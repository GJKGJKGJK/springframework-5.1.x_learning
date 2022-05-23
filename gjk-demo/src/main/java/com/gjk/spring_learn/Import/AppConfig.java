package com.gjk.spring_learn.Import;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.gjk.spring_learn.Import.annotation.EnableProxy;
import com.gjk.spring_learn.Import.annotation.ImportEu;
import com.gjk.spring_learn.Import.proxy.MyInvocationHandler;

/**
 * AppConfig
 *
 * 以java config方式启动Spring =》全配置类
 *
 * @author: GJK
 * @date: 2022/5/7 17:38
 * @description:
 */
@EnableProxy // 自定义注解，使用此注解=@Import(MyImportBeanDefinitionRegistry.class)
@ImportEu  // 自定义注解，使用此注解=@Import(MyImportSelector.class)
@Configuration
@ComponentScan("com.gjk.spring_learn.Import")
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
