package com.gjk.spring_learn.basis.Import.service.impl;

import org.springframework.stereotype.Service;

import com.gjk.spring_learn.basis.Import.dao.UserDapImpl;
import com.gjk.spring_learn.basis.Import.service.UserService;

/**
 * UserServiceImpl
 *
 * 未使用@Autowrited注解自动装配
 *
 * @author: GJK
 * @date: 2022/5/7 17:37
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

	private UserDapImpl userDapImpl;


	public void setUserDapImpl(UserDapImpl userDapImpl) {
		this.userDapImpl = userDapImpl;
	}


	@Override
	public void login() {
		userDapImpl.test();
		System.out.println("service层检验逻辑");
	}
}
