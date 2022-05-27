/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.Nullable;

/**
 * Delegate for AbstractApplicationContext's post-processor handling.
 *
 * @author Juergen Hoeller
 * @since 4.0
 */
final class PostProcessorRegistrationDelegate {

	private PostProcessorRegistrationDelegate() {
	}


	public static void invokeBeanFactoryPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {

		// Invoke BeanDefinitionRegistryPostProcessors first, if any.
		//首先定义一个容器，将已经解析过的BeanFactoryPostProcessorg缓存起来
		Set<String> processedBeans = new HashSet<>();

		if (beanFactory instanceof BeanDefinitionRegistry) {
			BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

			/**
			 * 此块代码处理的是用户自定义，且没有交由spring托管的BeanFactoryPostProcessor和BeanFactoryRegistryPostProcessor的实现类
			 *
			 * 做了什么？？？
			 * 用户已经实例化
			 * 对上述的实现类根据实现的接口分类,并调用BeanFactoryRegistryPostProcessor实现类的postProcessBeanDefinitionRegistry方法
			 *
			 * BeanFactoryPostProcessor 是 BeanFactoryRegistryPostProcessor的父接口，定义两个集合分别存放各自实现类
			 *
			 * 且优先调用BeanDefinitionRegistryPostProcessor接口的postProcessorBeanDefinitionRegistry方法
			 */
			//存放的是用户自定义的，且没有交由spring托管的BeanFactoryPostProcessor接口实现类
			List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();
			//存放的是所有的(用户定义的和Spring定义的)BeanFactoryRegistryPostProcessor的实现类
			List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();

			for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
				if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
					BeanDefinitionRegistryPostProcessor registryProcessor =
							(BeanDefinitionRegistryPostProcessor) postProcessor;
					registryProcessor.postProcessBeanDefinitionRegistry(registry);
					registryProcessors.add(registryProcessor);
				}
				else {
					regularPostProcessors.add(postProcessor);
				}
			}

			// Do not initialize FactoryBeans here: We need to leave all regular beans
			// uninitialized to let the bean factory post-processors apply to them!
			// Separate between BeanDefinitionRegistryPostProcessors that implement
			// PriorityOrdered, Ordered, and the rest.
			//这个集合用来存放spring内部的BeanDefinitionRegistryPostProcessor接口实现类，
			List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();

			// First, invoke the BeanDefinitionRegistryPostProcessors that implement PriorityOrdered.
			/**
			 * 目前只有一个实现类ConfigurationClassPostProcessor，
			 * 而用户定义的且交由Spring托管的BeanDefinitionRegistryPostProcessor接口实现类还没加载进容器
			 *
			 * 所以此块代码处理的是Spring定义的ConfigurationClassPostProcessor
			 * Spring先实例化ConfigurationClassPostProcessor
			 * 再调用ConfigurationClassPostProcessor的postProcessBeanDefinitionRegistry方法    核心啊啊啊啊啊！！！！！！
			 *
			 * 优先处理实现PriorityOrdered接口的bean，初始化这些bean并存入currentRegistryProcessors集合中
			 *
			 * PriorityOrdered接口对Ordered接口的扩展，表达了优先级排序，实现此接口的对象排序总是普通有序对象之前
			 */
			String[] postProcessorNames =
					beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					processedBeans.add(ppName);
				}
			}
			//对集合按照DefaultListableBeanFactory中的dependencyComparator排序规则进行排序
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			//将spring定义的和用户定义的BeanDefinitioanRegistryPostProcessor接口实现类合并，集中处理
			registryProcessors.addAll(currentRegistryProcessors);
			//循环调用各个BeanDefinitionRegistryPostProcessor接口实现类的postProcessorBeanDefinitionRegistry方法
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			//已经将currentRegistryProcessors集合加入到总的registryProcessors集合总，此时清空，给下面继续使用
			currentRegistryProcessors.clear();

			/**
			 * 此时用户自定义的，且交给Spring托管的Bean都已注册
			 * 此块代码处理的是用户自定义的、由Spring扫描出来的BeanDefinitionRegistryPostProcessor接口实现类
			 * 这里会调用上述实现类的PostProcessBeanDefinitionRegistry方法
			 *
			 * 优先处理实现Ordered接口且没有实现PriorityOrdered接口的实现类，实例化这些bean并存入currentRegistryProcessors集合中
			 */
			// Next, invoke the BeanDefinitionRegistryPostProcessors that implement Ordered.
			postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					processedBeans.add(ppName);
				}
			}
			//对集合按照DefaultListableBeanFactory中的dependencyComparator排序规则进行排序
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			//加入到registryProcessors集合中，集中处理
			registryProcessors.addAll(currentRegistryProcessors);
			//调用实现类的postProcessorBeanDefinitionRegistry方法
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			//清空
			currentRegistryProcessors.clear();

			/**
			 * 此块代码处理用户注册的，且交由Spring托管的BeanDefinitionRegistryPostProcessor实现类
			 * Spring先实例化用户注册的，且交由Spring托管的BeanDefinitionRegistryPostProcessor实现类
			 * 再调用其PostProcessBeanDefinitionRegistry方法
			 *
			 * 实例化这些bean并存入currentRegistryProcessors集合中
			 */
			// Finally, invoke all other BeanDefinitionRegistryPostProcessors until no further ones appear.
			boolean reiterate = true;
			while (reiterate) {
				reiterate = false;
				postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
				for (String ppName : postProcessorNames) {
					if (!processedBeans.contains(ppName)) {
						currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
						processedBeans.add(ppName);
						reiterate = true;
					}
				}
				//对集合按照DefaultListableBeanFactory中的dependencyComparator排序规则进行排序
				sortPostProcessors(currentRegistryProcessors, beanFactory);
				//加入到registryProcessors集合中，集中处理
				registryProcessors.addAll(currentRegistryProcessors);
				//调用实现类的postProcessorBeanDefinitionRegistry方法
				invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
				//清空
				currentRegistryProcessors.clear();
			}

			// Now, invoke the postProcessBeanFactory callback of all processors handled so far.
			/**
			 * 最后集中调用所有的BeanDefinitionRegistryPostProcessor接口实现类的postprocessorBeanFactory方法
			 * （小提示：BeanDefinitionRegistoryPostProcessor接口继承BeanFactoryPostProcessor接口）
			 */
			invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
			/**
			 * 集中调用用户自定义的，且没有交由spring托管的BeanFactoryPostProcessor接口实现类的postProcessorBeanFactory方法
			 */
			invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
		}

		else {
			/**
			 * 如果beanFactory没有实现BeanDefinitionRegistory接口，则直接调用BeanFactoryPostProcessor接口实现类的postProcessorBeanFactory方法
			 *
			 * 此代码块执行的是用户自定义的，且没有交由spring托管的BeanFactoryPostProcessor接口实现类的postProcessorBeanFactory方法
			 */
			// Invoke factory processors registered with the context instance.
			invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
		}

		/**
		 * ==================================================================================================================
		 * 至此！！！
		 * 所有的（用户定义且没有交给Spring托管、Spring定义的、用户定义且交给Spring托管的）BeanDefinitionRegistryPostProcessor实现类
		 * 的postProcessBeanDefinitionRegistry()方法和postProcessBeanFactory()方法全部执行完毕
		 *
		 * 用户定义的，且没有交由Spring托管的BeanFactoryPostProcessor实现类的postProcessBeanFactory()方法也已经全部执行完毕
		 *===================================================================================================================
		 */

		// Do not initialize FactoryBeans here: We need to leave all regular beans
		// uninitialized to let the bean factory post-processors apply to them!
		/**
		 *  此时用户定义的所有BeanFactoryPostProcessor已全部加载到容器，所以都能获取到
		 *  而且还有Spring定义的两个BeanFactory:ConfigurationClassPostProcessor 和 EventListenerMethodProcessor
		 *  但是ConfigurationClassPostProcessor在前面已经处理过，此时会跳过
		 *
		 *  所以此块代码处理的是交由Spring管理的所有实现BeanFactoryPostProcessor接口的实现以及Spring定义的EventListenerMethodProcessor
		 *  根据实现排序接口情况进行分类
		 *  并且Spring先实例化同时实现PriorityOrdered排序接口的BeanFactoryPostProcessor实现类
		 */
		String[] postProcessorNames =
				beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

		// Separate between BeanFactoryPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		List<String> orderedPostProcessorNames = new ArrayList<>();
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();

		for (String ppName : postProcessorNames) {
			if (processedBeans.contains(ppName)) {
				// skip - already processed in first phase above
			}
			else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
			}
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				orderedPostProcessorNames.add(ppName);
			}
			else {
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, invoke the BeanFactoryPostProcessors that implement PriorityOrdered.
		/**
		 * 处理同时实现PriorityOrdered接口的BeanFactoryPostProcessor实现类
		 * 排序，再调用postProcessorBeanFactory方法
		 */
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);

		// Next, invoke the BeanFactoryPostProcessors that implement Ordered.
		/**
		 *  处理同时实现ordered接口的BeanFactoryPostProcessor实现类
		 *  Spring先实例化上述的Bean
		 *  再排序，调用postProcessorBeanFactory方法
		 */
		List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>();
		for (String postProcessorName : orderedPostProcessorNames) {
			orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		sortPostProcessors(orderedPostProcessors, beanFactory);
		invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);

		// Finally, invoke all other BeanFactoryPostProcessors.
		/**
		 *  处理剩下的BeanFactoryPostProcessor实现类
		 *  Spring先实例化上述的Bean
		 *  因为没有实现排序接口，不排序
		 *  直接调用postProcessorBeanFactory方法
		 */
		List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>();
		for (String postProcessorName : nonOrderedPostProcessorNames) {
			nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);

		// Clear cached merged bean definitions since the post-processors might have
		// modified the original metadata, e.g. replacing placeholders in values...
		beanFactory.clearMetadataCache();
	}

	public static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {

		/**
		 * 从BeanDefinitionMap中获取BeanPostProcessor实现类
		 * 此时可以获取到两个Spring定义的BeanPostProcessor实现类和用户定义的BeanPostProcessor实现类
		 *
		 * Spring定义的BeanPostProcessor是在实例化上下文-->实例化AnnotatedBeanDefinitionReader时注册的
		 * 分别是AutowiredAnnotationProcessor和CommonAnnotationProcessor
		 */
		String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

		// Register BeanPostProcessorChecker that logs an info message when
		// a bean is created during BeanPostProcessor instantiation, i.e. when
		// a bean is not eligible for getting processed by all BeanPostProcessors.
		/**
		 * 此时beanFactory.beanPostProcessors集合有三个实现类(直接实例化了，没有注册到容器中交由spring初始化)：
		 * ApplicationContextAwareProcessor 在上下文执行refresh().prepareBeanFactory()添加的
		 * ApplicationListenerDetector  在上下文执行refresh().prepareBeanFactory()添加的
		 * ImportAwareBeanPostProcessor(ConfigurationClassPostProcessor的内部类)  在执行ConfigurationClassPostProcessor.PostProcessBeanFactory()方法是添加的
		 */
		int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
		/**
		 * 向BeanFactory的BeanPostProcessor集合添加BeanPostProcessorChecker(主要用于记录信息)
		 */
		beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

		// Separate between BeanPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		//存放实现PriorityOrdered接口的BeanPostProcessor
		List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		//存放实现MergedBeanDefinitionPostProcessor接口的BeanPostProcessor
		List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
		//存放实现Ordered接口的BeanPostProcessor
		List<String> orderedPostProcessorNames = new ArrayList<>();
		//没有实现排序接口的BeanPostProcessor
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();

		/**
		 * 优先处理实现PriorityOrdered接口的BeanPostProcessor
		 * 先实例化创建Bean对象
		 * 再添加到priorityOrderedPostProcessors集合中
		 * 如果还是实现了MeredBeanDefinitionPostProcessor接口，还会在internalPostProcessors集合中添加一遍
		 * 对priorityOrderedPostProcessors集合中的BeanProcessor排序
		 * 将priorityOrderedPostProcessors集合中BeanProcessor添加到BeanFactory的beanpostprocessors集合中
		 */
		for (String ppName : postProcessorNames) {
			if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
				priorityOrderedPostProcessors.add(pp);
				if (pp instanceof MergedBeanDefinitionPostProcessor) {
					internalPostProcessors.add(pp);
				}
			}
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				orderedPostProcessorNames.add(ppName);
			}
			else {
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, register the BeanPostProcessors that implement PriorityOrdered.
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

		/**
		 * 其次处理实现Ordered接口的BeanPostProcessor
		 * 先实例化创建Bean对象
		 * 再添加到orderedPostProcessors集合中
		 * 如果还是实现了MeredBeanDefinitionPostProcessor接口，还会在internalPostProcessors集合中添加一遍
		 * 对orderedPostProcessors集合中的BeanProcessor排序
		 * 将orderedPostProcessors集合中BeanProcessor添加到BeanFactory的beanPostProcessors集合中
		 */
		// Next, register the BeanPostProcessors that implement Ordered		.
		List<BeanPostProcessor> orderedPostProcessors = new ArrayList<>();
		for (String ppName : orderedPostProcessorNames) {
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			orderedPostProcessors.add(pp);
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}
		sortPostProcessors(orderedPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, orderedPostProcessors);

		// Now, register all regular BeanPostProcessors.
		/**
		 * 接着处理没有实现排序接口的BeanPostProcessor
		 * 先实例化创建Bean对象
		 * 再添加到nonOrderedPostProcessors集合中
		 * 如果还是实现了MeredBeanDefinitionPostProcessor接口，还会在internalPostProcessors集合中添加一遍
		 * 对nonOrderedPostProcessors集合中的BeanProcessor排序
		 * 将nonOrderedPostProcessors集合中BeanProcessor添加到BeanFactory的beanPostProcessors集合中
		 */
		List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<>();
		for (String ppName : nonOrderedPostProcessorNames) {
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			nonOrderedPostProcessors.add(pp);
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}
		registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

		// Finally, re-register all internal BeanPostProcessors.
		/**
		 * 最后将internalPostProcessors集合中的BeanPostProcessor在添加到BeanFactory的beanPostProcessors集合中
		 * 此处的BeanPostProcessor不需要实例化，因为前面的处理已经实例化了
		 * 这边处理的目的就是将实现MeredBeanDefinitionPostProcessor接口的BeanPostProcessor放到最后
		 */
		sortPostProcessors(internalPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, internalPostProcessors);

		// Re-register post-processor for detecting inner beans as ApplicationListeners,
		// moving it to the end of the processor chain (for picking up proxies etc).
		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
	}

	private static void sortPostProcessors(List<?> postProcessors, ConfigurableListableBeanFactory beanFactory) {
		Comparator<Object> comparatorToUse = null;
		if (beanFactory instanceof DefaultListableBeanFactory) {
			comparatorToUse = ((DefaultListableBeanFactory) beanFactory).getDependencyComparator();
		}
		if (comparatorToUse == null) {
			comparatorToUse = OrderComparator.INSTANCE;
		}
		postProcessors.sort(comparatorToUse);
	}

	/**
	 * Invoke the given BeanDefinitionRegistryPostProcessor beans.
	 */
	private static void invokeBeanDefinitionRegistryPostProcessors(
			Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {

		//此时只有一条数据 ConfigurationClassPostProcessor
		for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanDefinitionRegistry(registry);
		}
	}

	/**
	 * Invoke the given BeanFactoryPostProcessor beans.
	 */
	private static void invokeBeanFactoryPostProcessors(
			Collection<? extends BeanFactoryPostProcessor> postProcessors, ConfigurableListableBeanFactory beanFactory) {

		for (BeanFactoryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanFactory(beanFactory);
		}
	}

	/**
	 * Register the given BeanPostProcessor beans.
	 */
	private static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanPostProcessor> postProcessors) {

		for (BeanPostProcessor postProcessor : postProcessors) {
			beanFactory.addBeanPostProcessor(postProcessor);
		}
	}


	/**
	 * Spring的后置处理器之一
	 * 用来检查是否所有后置处理器都已执行，如果没有执行则报错
	 *
	 * BeanPostProcessor that logs an info message when a bean is created during
	 * BeanPostProcessor instantiation, i.e. when a bean is not eligible for
	 * getting processed by all BeanPostProcessors.
	 */
	private static final class BeanPostProcessorChecker implements BeanPostProcessor {

		private static final Log logger = LogFactory.getLog(BeanPostProcessorChecker.class);

		private final ConfigurableListableBeanFactory beanFactory;

		private final int beanPostProcessorTargetCount;

		public BeanPostProcessorChecker(ConfigurableListableBeanFactory beanFactory, int beanPostProcessorTargetCount) {
			this.beanFactory = beanFactory;
			this.beanPostProcessorTargetCount = beanPostProcessorTargetCount;
		}

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) {
			return bean;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) {
			if (!(bean instanceof BeanPostProcessor) && !isInfrastructureBean(beanName) &&
					this.beanFactory.getBeanPostProcessorCount() < this.beanPostProcessorTargetCount) {
				if (logger.isInfoEnabled()) {
					logger.info("Bean '" + beanName + "' of type [" + bean.getClass().getName() +
							"] is not eligible for getting processed by all BeanPostProcessors " +
							"(for example: not eligible for auto-proxying)");
				}
			}
			return bean;
		}

		private boolean isInfrastructureBean(@Nullable String beanName) {
			if (beanName != null && this.beanFactory.containsBeanDefinition(beanName)) {
				BeanDefinition bd = this.beanFactory.getBeanDefinition(beanName);
				return (bd.getRole() == RootBeanDefinition.ROLE_INFRASTRUCTURE);
			}
			return false;
		}
	}

}
