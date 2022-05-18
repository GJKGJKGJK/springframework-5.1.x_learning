package com.gjk.spring_learn.invokeBeanFactoryPostProcessor.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * ImportEuBeanFactoryProcessor
 *
 * 此类未加@Component注解,由ImportSelector接口实现类注册到容器
 *
 * @author: GJK
 * @date: 2022/5/7 17:43
 * @description:
 */
public class ImportEuBeanFactoryProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("============" + beanName + "================");
		return bean;
	}
}
