package com.github.qiaolin.apollo.test;

import com.github.qiaolin.apollo.test.properties.DepthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *  测试 Apollo热更新工具
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("apollo.cluster", "ql-test");
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

        DepthProperties bean = run.getBean(DepthProperties.class);

        System.out.println();
    }


}
