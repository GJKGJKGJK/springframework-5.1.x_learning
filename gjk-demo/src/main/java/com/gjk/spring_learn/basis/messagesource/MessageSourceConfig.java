package com.gjk.spring_learn.basis.messagesource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * MessageSourceConfig
 *
 * @author: GJK
 * @date: 2022/5/25 14:32
 * @description:
 */

@Configuration
public class MessageSourceConfig {

	@Bean(name = "messageSource")
	public MessageSource getMessageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.addBasenames("message","message_en");
		return messageSource;
	}
}
