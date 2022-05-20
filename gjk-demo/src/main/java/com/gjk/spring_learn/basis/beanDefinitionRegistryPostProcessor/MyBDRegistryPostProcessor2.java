package com.gjk.spring_learn.basis.beanDefinitionRegistryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * MyBDRegistryPostProcessor2
 *
 * @author: GJK
 * @date: 2022/5/19 22:22
 * @description:
 */
public class MyBDRegistryPostProcessor2 implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("=========MyBDRegistryPostProcessor2.postProcessBeanFactory()==========");

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("=========MyBDRegistryPostProcessor2.postProcessBeanDefinitionRegistry()==========");

	}
}
