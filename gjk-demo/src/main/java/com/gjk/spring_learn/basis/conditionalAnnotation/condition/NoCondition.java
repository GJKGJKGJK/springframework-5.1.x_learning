package com.gjk.spring_learn.basis.conditionalAnnotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * NoCondition
 *
 * @author: GJK
 * @date: 2022/5/13 15:31
 * @description:
 */
public class NoCondition implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return false;
	}
}
