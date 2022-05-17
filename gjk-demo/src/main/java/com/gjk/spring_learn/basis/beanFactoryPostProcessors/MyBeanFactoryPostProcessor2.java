package com.gjk.spring_learn.basis.beanFactoryPostProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * MyBeanFactoryPostProcessor2
 *
 * @author: GJK
 * @date: 2022/5/16 22:43
 * @description:
 */
public class MyBeanFactoryPostProcessor2 implements BeanFactoryPostProcessor, Ordered {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("=========MyBeanFactoryPostProcessor2==========");
		BeanDefinition studentServiceImpl = beanFactory.getBeanDefinition("studentServiceImpl");
		studentServiceImpl.setScope("single");
	}

	@Override
	public int getOrder() {
		return 200;
	}
}
