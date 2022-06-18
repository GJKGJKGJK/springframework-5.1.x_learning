package com.gjk.spring_learn.basis.test;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import com.gjk.spring_learn.basis.applicationEvent.event.MyApplicationEvent;
import com.gjk.spring_learn.basis.beanDefinitionRegistryPostProcessor.MyBDRegistryPostProcessor2;
import com.gjk.spring_learn.basis.beanFactoryPostProcessors.MyBeanFactoryPostProcessor2;
import com.gjk.spring_learn.basis.beanFactoryPostProcessors.MyBeanFactoryPostProcessor3;
import com.gjk.spring_learn.basis.factoryMethod.FactoryMethodTest1;
import com.gjk.spring_learn.basis.importAware.ImportArgs;
import com.gjk.spring_learn.basis.importAware.MyImportAware;
import com.gjk.spring_learn.basis.mergedLocalBeanDefinition.Child;
import com.gjk.spring_learn.basis.mergedLocalBeanDefinition.Parent;
import com.gjk.spring_learn.basis.service.IStudentService;
import com.gjk.spring_learn.basis.service.StudentServiceImpl;

/**
 * RunTest
 *
 * @author: GJK
 * @date: 2022/4/27 10:16
 * @description:
 */
@ComponentScan("com.gjk.spring_learn.basis")
//@ImportArgs(name = "GJK",age = 24)
//@ImportResource("classpath:Spring.xml")
@EnableAspectJAutoProxy
public class RunTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(RunTest.class);

		/**
		 * 可以在refresh之前向上下文中添加BeanFactoryPostProcessor
		 * 测试BeanFactoryPostProcessor执行顺序
		 */
//		context.addBeanFactoryPostProcessor(new MyBDRegistryPostProcessor2());
//		context.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor2());
//		context.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor3());
		context.refresh();
		IStudentService service = context.getBean(IStudentService.class);
		service.goSchool();

		/**
		 * 测试通过ImportAware接口+注解给属性设置值
		 */
//		MyImportAware bean = context.getBean(MyImportAware.class);
//		bean.print();

		/**
		 * 测试Spring的事件发布和事件监听
		 */
//		MyApplicationEvent event = new MyApplicationEvent(context, "GGGGBBB");
//		context.publishEvent(event);


		/**
		 * 测试单例Bean中注入原型Bean场景
		 */
//		StudentServiceImpl studentServiceImpl = (StudentServiceImpl) context.getBean("studentServiceImpl");
//		studentServiceImpl.goSchool();
//		System.out.println(studentServiceImpl.hashCode());
//		StudentServiceImpl studentServiceImpl1 = (StudentServiceImpl) context.getBean("studentServiceImpl");
//		studentServiceImpl1.goSchool();
//		System.out.println(studentServiceImpl1.hashCode());

		/**
		 * 国际化没搞定，尴尬！
		 */
//		MessageSource bean = context.getBean(MessageSource.class);
//		String zhMessage = bean.getMessage("高健康", null, null, Locale.CHINESE);
//		String enMessage = bean.getMessage("高健康", null, null, Locale.CHINESE);
//		System.out.println("zhMessage = "+zhMessage);
//		System.out.println("enMessage = "+enMessage);

		/**
		 * 测试执行初试化Bean时 BeanFactory的getMergedLocalBeanDefinition()方法的作用
		 */
//		Parent parent = (Parent) context.getBean("parent");
//		System.out.println(parent.getName());
//		Parent child = (Parent) context.getBean("child");
//		System.out.println(child.getName());


		/**
		 * 测试通过xml中的factory-method标签指定的方法创建bean实例
		 *
		 * 打印出来的结果是：com.gjk.spring_learn.basis.factoryMethod.FactoryMethodTest2@cad498c
		 */
//		System.out.println(context.getBean("factoryMethodTest1"));
	}
}
