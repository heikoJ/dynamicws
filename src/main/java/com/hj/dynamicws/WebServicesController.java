package com.hj.dynamicws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heiko on 27.03.16.
 */
@RestController
@RequestMapping("/rest/webServices")
public class WebServicesController {


    @Autowired
    WebServiceManager webServiceManager;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String newWebService() {
        System.out.println("Create new Service");
        webServiceManager.createWebService("testService");
        return "OK";
    }


}
