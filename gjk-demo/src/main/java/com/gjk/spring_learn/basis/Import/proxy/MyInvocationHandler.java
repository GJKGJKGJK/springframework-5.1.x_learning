package com.gjk.spring_learn.basis.Import.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * MyInvocationHandler
 *
 * @author: GJK
 * @date: 2022/5/7 17:36
 * @description:
 */
public class MyInvocationHandler implements InvocationHandler {

	private Object targetObject;

	public MyInvocationHandler(Object targetObject) {
		this.targetObject = targetObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("代理方法");
		return method.invoke(targetObject,args);
	}
}
