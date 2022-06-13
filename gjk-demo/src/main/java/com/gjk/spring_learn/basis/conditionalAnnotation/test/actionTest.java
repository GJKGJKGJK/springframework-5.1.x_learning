package com.gjk.spring_learn.basis.conditionalAnnotation.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gjk.spring_learn.basis.conditionalAnnotation.config.BeanLoadConfig;

/**
 * actionTest
 *
 * @author: GJK
 * @date: 2022/5/13 15:41
 * @description:
 */
public class actionTest {


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanLoadConfig.class);
	}
}
