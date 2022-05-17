package com.gjk.spring_learn.invokeBeanFactoryPostProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.annotation.EnableProxy;
import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.annotation.ImportEu;

/**
 * AppConfig
 *
 * 以java config方式启动Spring =》全配置类
 *
 * @author: GJK
 * @date: 2022/5/7 17:38
 * @description:
 */
@EnableProxy // 为UserServiceImpl类添加代理类的开关
@ImportEu  //导入ImportEuImportSelector后置处理器的开关
@Configuration
@ComponentScan("com.gjk.spring_learn.invokeBeanFactoryPostProcessor")
public class AppConfig {

	@Bean
	public TestBeanInAppConfig testBeanInAppConfig(){
		return new TestBeanInAppConfig();
	}

//	@Component
//	class ProcessMemberClasses1{
//
//	}
//
//	@Configuration
//	class ProcessMemberClasses2{
//
//	}
//
//
//	class ProcessMemberClasses3{
//
//	}
}
