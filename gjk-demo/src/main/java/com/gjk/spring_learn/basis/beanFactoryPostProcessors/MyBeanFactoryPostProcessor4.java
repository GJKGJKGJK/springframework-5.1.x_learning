package com.gjk.spring_learn.basis.beanFactoryPostProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * MyBeanFactoryPostProcessor4
 *
 * @author: GJK
 * @date: 2022/5/16 22:59
 * @description:
 */
//@Component
public class MyBeanFactoryPostProcessor4 implements BeanFactoryPostProcessor, Ordered {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("=========MyBeanFactoryPostProcessor4==========");

	}

	@Override
	public int getOrder() {
		return 400;
	}
}
