package com.gjk.spring_learn.basis.applicationEvent.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.gjk.spring_learn.basis.applicationEvent.event.MyApplicationEvent;

/**
 * MyApplicationListener
 *
 * 实现ApplicationListener接口的方式
 *
 * @author: GJK
 * @date: 2022/5/25 17:20
 * @description:
 */
@Component
public class MyApplicationListener1 implements ApplicationListener<MyApplicationEvent> {


	@Override
	public void onApplicationEvent(MyApplicationEvent event) {
		System.out.println("MyApplicationListener1 收到消息: " + event.getMessage());
	}



}
