package com.gjk.spring_learn.basis.beanDefinitionRegistryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * MyBDRegistryPostProcessor1
 *
 * @author: GJK
 * @date: 2022/5/19 22:22
 * @description:
 */
//@Component
public class MyBDRegistryPostProcessor1 implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("=========MyBDRegistryPostProcessor1.postProcessBeanDefinitionRegistry()==========");

	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("=========MyBDRegistryPostProcessor1.postProcessBeanFactory()==========");
	}
}
