package com.gjk.spring_learn.basis.Import.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.gjk.spring_learn.basis.Import.proxy.MyInvocationHandler;
import com.gjk.spring_learn.basis.Import.proxy.ProxyUtil;
import com.gjk.spring_learn.basis.Import.service.impl.UserServiceImpl;

/**
 * JDKProxyPostProcessor
 *
 * 此类未加@Component注解,由ImportBeanDefnitionRegistry接口实现类注册到容器
 *
 * @author: GJK
 * @date: 2022/5/7 17:36
 * @description:
 */
public class JDKProxyPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("JDKProxyPostProcessor--After方法");
		if(beanName.equals("userServiceImpl")){
			return ProxyUtil.newInstance(UserServiceImpl.class.getInterfaces(),new MyInvocationHandler(bean));
		}

		return bean;
	}

	public void testPrint(){
		System.out.println("JDKProxyPostProcessor -----  testPrint");
	}
}
