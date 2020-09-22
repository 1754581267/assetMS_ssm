package bao.xy.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: Spring 工具类
 * @CreateTime: 2020-09-09-19-08
 */
public class SpringUtils {

    public static Object getBean(String name) {
        String s = "applicationContext.xml";
        ApplicationContext conf = new ClassPathXmlApplicationContext(s);
        Object bean = conf.getBean(name);
        return bean;
    }

}
