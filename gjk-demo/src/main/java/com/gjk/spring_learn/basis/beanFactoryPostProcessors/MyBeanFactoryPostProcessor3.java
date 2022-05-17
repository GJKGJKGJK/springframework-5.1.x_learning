package com.gjk.spring_learn.basis.beanFactoryPostProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * MyBeanFactoryPostProcessor3
 *
 * @author: GJK
 * @date: 2022/5/16 22:58
 * @description:
 */
public class MyBeanFactoryPostProcessor3 implements BeanFactoryPostProcessor, Ordered {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("=========MyBeanFactoryPostProcessor3==========");
	}

	@Override
	public int getOrder() {
		return 300;
	}
}
