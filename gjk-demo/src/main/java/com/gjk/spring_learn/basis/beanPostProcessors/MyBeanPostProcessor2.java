package com.gjk.spring_learn.basis.beanPostProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * MyBeanPostProcessor2
 *
 * @author: GJK
 * @date: 2022/5/16 14:07
 * @description:
 */
//@Component
public class MyBeanPostProcessor2 implements BeanPostProcessor, PriorityOrdered {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("==========MyBeanPostProcessor2=========before:" + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("==========MyBeanPostProcessor2=========after" + beanName);
		return bean;
	}

	@Override
	public int getOrder() {
		return 100;
	}
}
