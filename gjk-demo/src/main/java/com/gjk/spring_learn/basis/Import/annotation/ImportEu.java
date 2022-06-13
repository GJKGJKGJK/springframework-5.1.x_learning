package com.gjk.spring_learn.basis.Import.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.gjk.spring_learn.basis.Import.Import.MyImportSelector;

/**
 * ImportEu
 *
 * 动态添加ImportEuBeanFactoryProcessor
 * 若在AppConfig类中添加@ImportEu注解，则通过ImportSelector的实现类MyImportSelector动态的向容器中添加ImportEuBeanFactoryProcessor后置处理器的BD
 * 而不是通过context.registry()方法或者@Component注解等方式向容器中添加BD
 **
 * @author: GJK
 * @date: 2022/5/7 17:41
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportSelector.class)
public @interface ImportEu {
}
