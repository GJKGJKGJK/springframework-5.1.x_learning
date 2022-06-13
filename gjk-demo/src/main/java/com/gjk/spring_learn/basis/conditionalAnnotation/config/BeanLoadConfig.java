package com.gjk.spring_learn.basis.conditionalAnnotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.gjk.spring_learn.basis.conditionalAnnotation.condition.NoCondition;
import com.gjk.spring_learn.basis.conditionalAnnotation.condition.YesCondition;
import com.gjk.spring_learn.basis.conditionalAnnotation.service.Car;
import com.gjk.spring_learn.basis.conditionalAnnotation.service.Train;

/**
 * BeanLoadConfig
 *
 * @author: GJK
 * @date: 2022/5/13 15:35
 * @description:
 */
@Configuration
public class BeanLoadConfig {

	@Bean
	@Conditional(YesCondition.class)
	public Car getCar(){
		return new Car();
	}


	@Bean
	@Conditional(NoCondition.class)
	public Train getTrain(){
		return new Train();
	}
}
