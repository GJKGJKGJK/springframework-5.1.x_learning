package com.gjk.spring_learn.basis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gjk.spring_learn.basis.service.StudentServiceImpl2;

/**
 * StudentConfig
 *
 * @author: GJK
 * @date: 2022/5/23 10:41
 * @description:
 */
@Configuration
public class StudentConfig {

	@Bean
	public StudentServiceImpl2 getStudentServiceImpl2(){
		return new StudentServiceImpl2();
	}
}
