package com.gjk.spring_learn.basis.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * MyApplicationContext
 *
 * @author: GJK
 * @date: 2022/5/16 18:04
 * @description:
 */
//@Component
public class MyApplicationContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		MyApplicationContext.applicationContext = applicationContext;
	}



	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}
}
