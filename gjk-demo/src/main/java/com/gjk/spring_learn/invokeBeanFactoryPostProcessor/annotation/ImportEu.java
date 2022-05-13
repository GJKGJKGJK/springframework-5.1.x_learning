package com.gjk.spring_learn.invokeBeanFactoryPostProcessor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.Import.MyImportSelector;

/**
 * ImportEu
 *
 * 动态添加ImportEuBeanFactoryProcessor
 * 若在AppConfig类中添加@ImportEu注解，则会添加ImportEuBeanFactoryProcessor后置处理器
 * 否则不添加
 *
 * @ImportEu注解的作用就是将添加注解的类导入，最终spring会将selectImports方法中返回的字符串数组转成eban
 *
 * @author: GJK
 * @date: 2022/5/7 17:41
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportSelector.class)
public @interface ImportEu {
}
