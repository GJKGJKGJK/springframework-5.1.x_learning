package com.gjk.spring_learn.basis.Import.Import;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.gjk.spring_learn.basis.Import.annotation.ImportEu;
import com.gjk.spring_learn.basis.Import.postprocessor.ImportEuBeanFactoryProcessor;

/**
 * MyImportSelector
 *
 * 自定义的ImportSelector
 * 实现一个功能：若在导入类中添加@ImportEu注解时，在每个bean创建之前打印一句“=========="+ beanName +"==========”
 *
 * @author: GJK
 * @date: 2022/5/7 17:42
 * @description:
 */
public class MyImportSelector implements ImportSelector {

	/**
	 *
	 * @param importingClassMetadata  这里的Metadata是AppConfig类的元数据，根据Metadata，我们可以获取到AppConfig类中各种信息
	 *
	 * @return   spring内部会将返回的字符串数组通过反射生成Bean,转成BeanDefinition注册到容器中
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
