package com.edmtz;

import com.edmtz.config.general.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {

        ApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class
        );

        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("application-context.xml");

        System.out.println("EJEMPLO!");
    }
}
