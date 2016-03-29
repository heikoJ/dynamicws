package com.hj.dynamicws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

/**
 * Created by heiko on 29.03.16.
 */
@Configuration
public class Config {


    @Bean
    public DynamicMessageDispatcherServlet dynamicMessageDispatcherServlet() {

        return new DynamicMessageDispatcherServlet();

    }




}
