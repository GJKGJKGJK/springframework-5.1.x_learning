package com.gjk.spring_learn.basis.dao;

import org.springframework.stereotype.Repository;

/**
 * StudentDaoImpl
 *
 * @author: GJK
 * @date: 2022/4/27 10:05
 * @description:
 */
@Repository
public class StudentDaoImpl implements IStudentDao{
	@Override
	public void byBicycle() {
		System.out.println("byBicycle");
	}
}
