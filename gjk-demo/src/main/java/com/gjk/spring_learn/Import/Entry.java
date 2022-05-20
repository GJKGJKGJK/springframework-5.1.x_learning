package com.gjk.spring_learn.Import;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gjk.spring_learn.Import.postprocessor.JDKProxyPostProcessor;
import com.gjk.spring_learn.Import.service.UserService;

/**
 * Entry
 *
 * 入口
 *
 * @author: GJK
 * @date: 2022/5/7 17:38
 * @description:
 */
public class Entry {


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();

		JDKProxyPostProcessor jdkProxyPostProcessor = (JDKProxyPostProcessor) context.getBean("JDKProxyPostProcessor");
		jdkProxyPostProcessor.testPrint();

		//获取UserService类型的对象
		context.getBean(UserService.class).login();

		//测试AppConfig类已@Bean方式创建TestBeanInAppConfig类是否存在
		System.out.println(context.getBean(TestBeanInAppConfig.class));

		//测试UserDaoImpl类已@Bean方式创建TestDaoInUserDaoImpl类是否存在
		System.out.println(context.getBean(TestDaoInUserDaoImpl.class));

	}
}
