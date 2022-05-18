package com.gjk.spring_learn.invokeBeanFactoryPostProcessor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.Import.MyImportBeanDefinitionRegistrar;

/**
 * EnableProxy
 *
 * 使用@EnableProxy动态地将@Import注解中的类导入Spring，由容器托管
 *
 * @author: GJK
 * @date: 2022/5/7 16:59
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface EnableProxy {
}
