package com.gjk.spring_learn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.gjk.spring_learn.basis.beanDefinitionRegistryPostProcessor.MyBDRegistryPostProcessor2;
import com.gjk.spring_learn.basis.beanFactoryPostProcessors.MyBeanFactoryPostProcessor2;
import com.gjk.spring_learn.basis.beanFactoryPostProcessors.MyBeanFactoryPostProcessor3;

/**
 * sumTest
 *
 * @author: GJK
 * @date: 2022/5/20 21:39
 * @description:
 */
@ComponentScan("com.gjk.spring_learn")
public class sumTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(sumTest.class);


		//可以在refresh之前向上下文中添加BeanFactoryPostProcessor
		context.addBeanFactoryPostProcessor(new MyBDRegistryPostProcessor2());
		context.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor2());
		context.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor3());

		context.refresh();


	}
}
