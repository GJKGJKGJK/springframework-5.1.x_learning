package com.gjk.spring_learn.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.gjk.spring_learn.service.StudentServiceImpl;

/**
 * RunTest
 *
 * @author: GJK
 * @date: 2022/4/27 10:16
 * @description:
 */
@ComponentScan("com.gjk")
public class RunTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunTest.class);
		StudentServiceImpl studentServiceImpl = (StudentServiceImpl) context.getBean("studentServiceImpl");
		studentServiceImpl.goSchool();

	}
}
