package com.gjk.spring_learn.basis.importAware;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * ImportArgs
 *
 * @author: GJK
 * @date: 2022/5/27 17:54
 * @description:
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({MyImportAware.class})
public @interface ImportArgs {
	String name() default "";

	int age() default 0;

}
