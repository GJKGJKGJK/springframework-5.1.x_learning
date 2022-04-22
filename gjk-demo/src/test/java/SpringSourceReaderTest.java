import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gjk.spring_learn.Student;

/**
 * SpringSourceReaderTest
 *
 * @author: GJK
 * @date: 2022/4/22 10:31
 * @description:
 */
public class SpringSourceReaderTest {


	@Test
	public void testSpringSourceReader(){
		//容器初始化
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Student bean = applicationContext.getBean(Student.class);
		System.out.println(bean.toString());
	}
}
