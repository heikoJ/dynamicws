package com.hj.dynamicws;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by heiko on 25.09.15.
 */
@SpringBootApplication
@Configuration
public class Application implements ApplicationContextAware{


    private ApplicationContext applicationContext;

    public static void main(String [] args) {
        SpringApplication.run(Application.class);
    }




    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext(applicationContext);

        DispatcherServlet servlet1 = new  DispatcherServlet();


        context1.getBeanFactory().registerSingleton("servlet1",servlet1);





    }




}
