package com.hj.dynamicws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.xml.xsd.XsdSchema;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by heiko on 29.03.16.
 */


    @Endpoint
    public class CountryEndpoint {
        private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";




        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest" )
        @ResponsePayload
        public Element getCountry(@RequestPayload Element request)throws Exception {

            NodeList nodes = request.getElementsByTagNameNS(NAMESPACE_URI,"code");

            String code = nodes.item(0).getTextContent();

                  System.out.println("code: " + code);


            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document doc = builder.newDocument();
            // create the root element node

            Element element = doc.createElement("root");
            doc.appendChild(element);

            Element getCountryReponse = doc.createElement("getCountryResponse");
            element.appendChild(getCountryReponse);


            Element name = doc.createElement("name");
            name.setTextContent("TestName" + code);

            getCountryReponse.appendChild(name);



            return getCountryReponse;
        }
    }


