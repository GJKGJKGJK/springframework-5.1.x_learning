package com.gjk.spring_learn.basis.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.gjk.spring_learn.basis.beanFactoryPostProcessors.MyBeanFactoryPostProcessor2;
import com.gjk.spring_learn.basis.beanFactoryPostProcessors.MyBeanFactoryPostProcessor3;
import com.gjk.spring_learn.basis.service.StudentServiceImpl;

/**
 * RunTest
 *
 * @author: GJK
 * @date: 2022/4/27 10:16
 * @description:
 */
@ComponentScan("com.gjk.spring_learn.basis")
public class RunTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(RunTest.class);

		//可以在refresh之前向上下文中添加BeanFactoryPostProcessor
		context.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor2());
		context.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor3());

		context.refresh();


		StudentServiceImpl studentServiceImpl = (StudentServiceImpl) context.getBean("studentServiceImpl");
		studentServiceImpl.goSchool();
		System.out.println(studentServiceImpl.hashCode());
		StudentServiceImpl studentServiceImpl1 = (StudentServiceImpl) context.getBean("studentServiceImpl");
		studentServiceImpl1.goSchool();
		System.out.println(studentServiceImpl1.hashCode());

	}
}
