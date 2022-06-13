package com.gjk.spring_learn.basis.Import.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.gjk.spring_learn.basis.Import.TestDaoInUserDaoImpl;

/**
 * UserDapImpl
 *
 * 模拟dao层对象
 *
 * @author: GJK
 * @date: 2022/5/7 17:05
 * @description:
 */
@Repository
public class UserDapImpl {

	/**
	 * 这里先引入一个问题：这个bean会不会被创建？
	 */
	@Bean
	public TestDaoInUserDaoImpl testDaoInUserDao() {
		return new TestDaoInUserDaoImpl();
	}

	public void test(){
		System.out.println("dao模拟查询数据逻辑");
	}
}
