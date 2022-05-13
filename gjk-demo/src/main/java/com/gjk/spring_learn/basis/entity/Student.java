package com.gjk.spring_learn.basis.entity;

/**
 * Student
 *
 * @author: GJK
 * @date: 2022/4/22 10:28
 * @description:
 */
public class Student {



	private int id;

	private String username;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", username='" + username + '\'' +
				'}';
	}


}
