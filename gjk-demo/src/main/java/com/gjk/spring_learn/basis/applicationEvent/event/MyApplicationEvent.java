package com.gjk.spring_learn.basis.applicationEvent.event;

import org.springframework.context.ApplicationEvent;

/**
 * MyApplicationEvent
 * 事件
 *
 * @author: GJK
 * @date: 2022/5/25 17:20
 * @description:
 */
public class MyApplicationEvent extends ApplicationEvent {

	private String message;

	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public MyApplicationEvent(Object source,String message) {
		super(source);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
