package com.gjk.spring_learn.basis.Import.Import;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.gjk.spring_learn.basis.Import.annotation.EnableProxy;
import com.gjk.spring_learn.basis.Import.postprocessor.JDKProxyPostProcessor;

/**
 * MyImportBeanDefinitionRegistry
 *
 * 模拟SpringAop ,在这里添加一个创建JDK动态代理的后置处理器
 *
 * @author: GJK
 * @date: 2022/5/7 17:07
 * @description:
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	/**
	 *
	 * @param importingClassMetadata 这里的Metadata是AppConfig类的元数据，根据Metadata，我们可以获取到AppConfig类中各种信息
	 * @param registry 上下文中的BeanFactory,我们可以通过Registry手动注入BeanDefiniition
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		if(importingClassMetadata.hasAnnotation(EnableProxy.class.getName())){
			GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) registry.getBeanDefinition("userServiceImpl");

			// 模拟mybatis的SqlSessionFactoryBean,为MapperScan对象设置自动装配模式
			//ps: UserServiceImpl类中的UserDaoImpl属性没有添加@Autowrited注解，只有set方法，若不添加此行代码，属性注入不进去
			genericBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(JDKProxyPostProcessor.class);
			registry.registerBeanDefinition(JDKProxyPostProcessor.class.getSimpleName(),rootBeanDefinition);
		}
	}
}
