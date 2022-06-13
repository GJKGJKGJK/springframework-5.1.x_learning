package com.gjk.spring_learn.basis.conditionalAnnotation.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * YesCondition
 *
 * @author: GJK
 * @date: 2022/5/13 15:30
 * @description:
 */
public class YesCondition implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 获取上下文中的BeanFactory
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		// 获取上下文中的类加载器
		ClassLoader classLoader = context.getClassLoader();
		// 获取上下文中的环境信息
		Environment environment = context.getEnvironment();
		// 获取上下文中的注册表
		BeanDefinitionRegistry registry = context.getRegistry();
		return true;
	}
}
