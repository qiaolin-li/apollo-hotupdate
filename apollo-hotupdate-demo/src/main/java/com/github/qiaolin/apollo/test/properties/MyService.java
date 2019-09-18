package com.github.qiaolin.apollo.test.properties;


import com.github.qiaolin.apollo.test.enumerate.MyEnum;
import com.github.qiaolin.apollo.annotation.HotUpdateFiled;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyService {

    @HotUpdateFiled(value = "com.github.qiaolin.day", defaultValue = "9")
    private int day;

    @HotUpdateFiled(prefix = "com.github.qiaolin", value = "a")
    private String a;


    @Value(value = "${qiaolin.a.b:1}")
    private String b;


    @Value("${xx.zz}")
    private Date dates;

    @Value("${xx.aa}")
    private MyEnum[] enums;



}
