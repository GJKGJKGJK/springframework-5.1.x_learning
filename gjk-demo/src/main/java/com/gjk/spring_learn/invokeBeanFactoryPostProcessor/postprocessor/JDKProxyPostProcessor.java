package com.gjk.spring_learn.invokeBeanFactoryPostProcessor.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.proxy.MyInvocationHandler;
import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.proxy.ProxyUtil;
import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.service.impl.UserServiceImpl;

/**
 * JDKProxyPostProcessor
 *
 * 动态代理后置处理器，这里为userServiceImpl创建代理对象
 *
 * @author: GJK
 * @date: 2022/5/7 17:36
 * @description:
 */
@Component
public class JDKProxyPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		if(beanName.equals("userServiceImpl")){
			return ProxyUtil.newInstance(UserServiceImpl.class.getInterfaces(),new MyInvocationHandler(bean));
		}

		return bean;
	}
}
