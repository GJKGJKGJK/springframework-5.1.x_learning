package com.gjk.spring_learn.invokeBeanFactoryPostProcessor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.Import.MyImportBeanDefinitionRegistry;

/**
 * EnableProxy
 *
 * 若在AppConfig类中无@EnableProxy注解，则不代理
 * 否则代理
 *
 * 此注解就是将被注解类导入，由spring内部实例化对象，调用它的registoryBeanDefinition方法，来添加JDKProxyProcessor对应的beanDefinition,最终Spring会根据beanDefinition创建Bean
 *
 * @author: GJK
 * @date: 2022/5/7 16:59
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportBeanDefinitionRegistry.class)
public @interface EnableProxy {
}
