package com.gjk.spring_learn.invokeBeanFactoryPostProcessor.Import;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.annotation.ImportEu;
import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.postprocessor.ImportEuBeanFactoryProcessor;

/**
 * MyImportSelector
 *
 * 由@ImportEu注解将此类导入Spring
 *
 * 此类又会向容器导入ImportEuBeanFactoryProcessor类
 *
 * @author: GJK
 * @date: 2022/5/7 17:42
 * @description:
 */
public class MyImportSelector implements ImportSelector {

	/**
	 * 理论上Spring会将 实现ImportSelector接口实现类的selectImports方法的返回字符串数组转化成Bean
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		if(importingClassMetadata.hasAnnotation(ImportEu.class.getName())){
			return new String[]{
					ImportEuBeanFactoryProcessor.class.getName()
			};
		}
		return new String[0];
	}
}
