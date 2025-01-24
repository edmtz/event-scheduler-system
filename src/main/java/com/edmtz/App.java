package com.edmtz;

import com.edmtz.config.WebMvcConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context = new AnnotationConfigApplicationContext(
                WebMvcConfig.class
        );

        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("application-context.xml");
    }
}
