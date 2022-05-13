package com.gjk.spring_learn.invokeBeanFactoryPostProcessor.Import;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.annotation.ImportEu;
import com.gjk.spring_learn.invokeBeanFactoryPostProcessor.postprocessor.ImportEuBeanFactoryProcessor;

/**
 * MyImportSelector
 *
 * 自定义的ImportSelector
 * 实现一个功能：若在导入类中添加@ImportEu注解时，在每个bean创建之前打印一句“==========ImportEu==========”
 *
 * @author: GJK
 * @date: 2022/5/7 17:42
 * @description:
 */
public class MyImportSelector implements ImportSelector {

	/**
	 *
	 * @param importingClassMetadata  能获取到被导入的那个类的信息，比如在类A中使用@Import注解将当前类(MyImportSelector)引入，
	 *                                   那么importingClassMetadata中存的信息就是类A
	 * @return   spring内部会将返回的字符串数组也当成bean
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
