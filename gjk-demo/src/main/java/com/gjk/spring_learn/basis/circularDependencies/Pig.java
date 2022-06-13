package com.gjk.spring_learn.basis.circularDependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Pig
 *
 * @author: GJK
 * @date: 2022/6/12 1:24
 * @description:
 */
@Service
public class Pig {

	@Autowired
	private Dog dog;
}
