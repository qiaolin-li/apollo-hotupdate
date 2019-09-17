package com.github.qiaolin.apollo.test.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.qiaolin.apollo.test.properties.ArrayProperties;
import com.github.qiaolin.apollo.test.properties.MyProperties;
import com.github.qiaolin.apollo.test.properties.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private MyProperties myProperties;

    @Autowired
    private ArrayProperties arrayProperties;

    @Autowired
    private MyService myService;

    @RequestMapping("hello")
    public String hello() throws JsonProcessingException {
        return objectMapper.writeValueAsString(myProperties);
    }
    @RequestMapping("hello1")
    public String hello1() throws JsonProcessingException {
        return objectMapper.writeValueAsString(arrayProperties);
    }
    @RequestMapping("hello2")
    public String hello2() throws JsonProcessingException {
        return objectMapper.writeValueAsString(myService);
    }
}
