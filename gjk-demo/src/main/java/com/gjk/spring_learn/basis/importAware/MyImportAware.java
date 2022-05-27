package com.gjk.spring_learn.basis.importAware;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * MyImportAware
 *
 * 一般情况下我们都是通过配置文件yml、properties等设置一些配置值，
 * Spring其实还提供通过注解方式+实现ImportAware接口的方式，设置一些配置值
 * 实现ImportAware接口，我们可以获取到指定注解的属性和值
 *
 * 主要就是在ConfigurationClassPostProcessor中注入了一个ImportAwareBeanPostProcessor，在Bean的生命周期中将属性设置进去
 *
 *
 * @author: GJK
 * @date: 2022/5/27 17:45
 * @description:
 */

  //此处必须是@Configuration注解，否则不生效
public class MyImportAware implements ImportAware {



	private String name;

	private Integer age;

	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		//获取注解类的属性值
		Map<String, Object> map = importMetadata.getAnnotationAttributes(ImportArgs.class.getName());
		AnnotationAttributes attributes = AnnotationAttributes.fromMap(map);
		name = attributes.getString("name");
		age = attributes.getNumber("age");
	}


	public void print(){
		System.out.println("+++++name:" + name);
		System.out.println("+++++age:" + age);
	}

}
