package com.gjk.spring_learn.basis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * StudentConfig
 *
 * 这个config包里代码是测试FullAnnotation类和LiteAnnotation类的区别
 * Full注解：@Configuration注解；
 * Lite注解：@Component、@Import、@ImportResource、@ComponentScan；
 *
 *
 * 通过 类加载到BeanDefnitionMap 的查看源码，我们发现Full注解和Lite注解，是没有区别的，
 * 那问题来了！！！为什么Spring要对类区分Full注解和Lite注解呢？
 *
 * 这里的@Configuration,我们使用@Component、@Import、@ImportResource、@ComponentScan也是一样的效果
 * 使用@Configuration和使用@Component一样，下面的两个@Bean方法都会调用构造方法，计算和打印hash值
 *
 * 当我们使用@Component注解，同时在 getFullAndLiteServiceImpl2() 方法中调用 getFullAndLiteServiceImpl1() 方法
 * 我们会发现FullAndLiteServiceImpl1类调用了两次构造方法并且两次hash值都不同，一次Spring初始化调用构造方法，一次调用getFullAndLiteServiceImpl1()实例化时调用构造方法
 * 但是当我们使用@Configuration，做相同操作时
 * 我们会发现FullAndLiteServiceImpl1类调用了一次构造方法，并没有出现使用@Component注解的同样情况
 *
 * 这里可以说明，虽然在包扫描注册阶段，Spring并没有对Full注解和Lite注解做区别处理，但是在@Bean的方法调用上肯定做了区别对待
 *
 *
 *
 * 引发问题：为什么@Configuration可以保持单例性，@Component不能？？？Spring到底对full注解做了什么操作？？？
 *
 * 详解请查看：
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor#postProcessBeanFactory
 * (org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
 *
 * 上面的代码描述总结：在实例化之前，Spring会检查BeanDefnitionMap中是否存在full注解Bean。
 * 当存在是，Spring会通过cglib方式，对full注解的bean创建代理对象，同时设置超类，类加载器，实现BeanFactoryAware接口
 * 并且为代理对象添加两个拦截器：BeanMethodIntercept 和 BeanFactoryAwareMethodIntercept
 *
 * 首先，我们获取使用@Configuration注解bean的实例时，Spring返回的是@Configuration注解bean的代理对象,
 * 当我们调用代理对象的@Bean方法时，BeanMethodinterept拦截器发挥作用，对@Bean方法进行拦截，从容器中获取@Bean方法实例返回
 * 这样确保了，Spring的单例性原则
 * 当我们调用代理对象的setBeanFactory方法时(实现BeanFactoryAware接口重写的方法)，BeanFactoryAwareMethodIntercept拦截器发挥作用，给代理对象设置BeanFactory属性
 *
 *
 * @author: GJK
 * @date: 2022/5/23 10:41
 * @description:
 */
//@Configuration
//@Component
public class FullAndLiteConfig {


	@Bean
	public FullAndLiteServiceImpl1 getFullAndLiteServiceImpl1(){
		return new FullAndLiteServiceImpl1();
	}

	@Bean
	public FullAndLiteServiceImpl2 getFullAndLiteServiceImpl2(){
		getFullAndLiteServiceImpl1();
		return new FullAndLiteServiceImpl2();
	}
}
