package com.gjk.spring_learn.basis.Import.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.gjk.spring_learn.basis.Import.Import.MyImportBeanDefinitionRegistrar;

/**
 * EnableProxy
 *
 * 若在AppConfig类中添加@EnableProxy注解，则通过ImportBeanDefinitionRegistry的实现类MyImportBeanDefinitionRegistry动态的向容器中添加JDKProxyPostProcessor后置处理器的BD
 * 而不是通过context.registry()方法或者@Component注解等方式向容器中添加BD
 **
 * @author: GJK
 * @date: 2022/5/7 16:59
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface EnableProxy {
}
