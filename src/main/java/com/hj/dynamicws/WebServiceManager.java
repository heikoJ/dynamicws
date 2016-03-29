package com.hj.dynamicws;

import com.hj.dynamicws.WebServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.transport.http.WsdlDefinitionHandlerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by heiko on 27.03.16.
 */
@Service
public class WebServiceManager {

    @Autowired
    DynamicMessageDispatcherServlet dynamicMessageDispatcherServlet;

    @Autowired
    ApplicationContext applicationContext;


    public void createWebService(String name) {



        XsdSchema schema = new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
        addBean(schema, "countriesSchema");

        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();



        wsdl11Definition.setPortTypeName(name + "Port");
        wsdl11Definition.setLocationUri("/ws/" + name);
        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
        wsdl11Definition.setSchema(schema);


        addBean(wsdl11Definition,name);




     dynamicMessageDispatcherServlet.refreshDefinitions();


    }


    private void addBean(Object bean,String name) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ((AbstractApplicationContext)applicationContext).getBeanFactory();
        try {
            beanFactory.registerSingleton(name,bean);
            Method method = ReflectionUtils.findMethod(bean.getClass(),"afterPropertiesSet");
            if(method!=null) {
                method.invoke(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
