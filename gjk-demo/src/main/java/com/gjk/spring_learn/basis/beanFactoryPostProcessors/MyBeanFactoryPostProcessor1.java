package com.gjk.spring_learn.basis.beanFactoryPostProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * MyBeanFactoryPostProcessor
 *
 * @author: GJK
 * @date: 2022/5/16 22:00
 * @description:
 */
//@Component
public class MyBeanFactoryPostProcessor1 implements BeanFactoryPostProcessor, Ordered {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("=========MyBeanFactoryPostProcessor1==========");
		BeanDefinition studentServiceImpl = beanFactory.getBeanDefinition("studentServiceImpl");
		studentServiceImpl.setScope("prototype");
	}

	@Override
	public int getOrder() {
		return 100;
	}
}
