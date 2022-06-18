package com.gjk.spring_learn.basis.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * SchoolAspectJ
 *
 * @author: GJK
 * @date: 2022/6/19 1:04
 * @description:
 */
@Component
@Aspect
public class SchoolAspectJ {

	@Pointcut("execution(* com.gjk.spring_learn.basis.service.IStudentService.goSchool())")
	private void goSchool(){};


	@Before("goSchool()")
	public void beforeGoSchool(JoinPoint joinPoint){
		System.out.println("==========beforeGoSchool");
	}

	@After("goSchool()")
	public void afterGoSchool(JoinPoint joinPoint){
		System.out.println("==========afterGoSchool");
	}
}
