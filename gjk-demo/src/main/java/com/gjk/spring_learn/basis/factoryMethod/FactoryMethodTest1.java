package com.gjk.spring_learn.basis.factoryMethod;

/**
 * FactoryMethodTest1
 *
 * @author: GJK
 * @date: 2022/6/6 0:16
 * @description:
 */
public class FactoryMethodTest1 {

	public static Object getFactoryMethodTest2(){
		return new FactoryMethodTest2();
	}


	public void print(){
		System.out.println("========FactoryMethodTest1===========");
	}
}
