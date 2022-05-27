package com.gjk.spring_learn.basis.applicationEvent.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.gjk.spring_learn.basis.applicationEvent.event.MyApplicationEvent;

/**
 * MyApplicationListener2
 *
 * @author: GJK
 * @date: 2022/5/25 17:30
 * @description:
 */
@Component
public class MyApplicationListener2 {


	@EventListener(MyApplicationEvent.class)
	public void eventListener(MyApplicationEvent event){
		System.out.println("MyApplicationListener2 收到消息: " + event.getMessage());
	}
}
