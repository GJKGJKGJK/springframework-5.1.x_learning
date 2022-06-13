package com.gjk.spring_learn.basis.Import.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ProxyUtil
 *
 * 代理工具对象
 *
 * @author: GJK
 * @date: 2022/5/7 17:36
 * @description:
 */
public class ProxyUtil {


	public static Object newInstance(Class<?>[] interfaces, InvocationHandler invocationHandler){
		return Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(),interfaces,invocationHandler);
	}
}
