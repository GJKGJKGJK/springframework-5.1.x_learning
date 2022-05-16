package com.gjk.spring_learn.basis.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;

import com.gjk.spring_learn.basis.aware.MyApplicationContext;
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
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunTest.class);
		StudentServiceImpl studentServiceImpl = (StudentServiceImpl) context.getBean("studentServiceImpl");
		studentServiceImpl.goSchool();
		StudentServiceImpl studentServiceImpl1 = (StudentServiceImpl) MyApplicationContext.getBean("studentServiceImpl");
		studentServiceImpl1.goSchool();

	}
}
