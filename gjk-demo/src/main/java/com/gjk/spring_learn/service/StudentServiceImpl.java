package com.gjk.spring_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gjk.spring_learn.dao.StudentDaoImpl;

/**
 * StudentServiceImpl
 *
 * @author: GJK
 * @date: 2022/4/27 10:06
 * @description:
 */

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private StudentDaoImpl studentDao;


	@Override
	public void goSchool() {
		System.out.println("goSchool");
		studentDao.byBicycle();
	}
}
