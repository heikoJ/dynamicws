package com.hj.dynamicws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

/**
 * Created by heiko on 29.03.16.
 */
public class DynamicMessageDispatcherServlet extends MessageDispatcherServlet {


    @Autowired
    ApplicationContext applicationContext;

    public void refreshDefinitions() {
        initStrategies(applicationContext);
    }



}
