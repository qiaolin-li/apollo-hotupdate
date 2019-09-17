package com.github.qiaolin.apollo.test.properties;


import com.github.qiaolin.apollo.test.enumerate.MyEnum;
import com.github.qiaolin.apollo.annotation.DefaultValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * @author Administrator
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "com.github.qiaolin")
public class MyProperties {

    @DefaultValue("1")
    private Integer prpo1;
    private String prpo2;
    private Long  prpo3;
    private Short prpo4;
    private Float prpo5;
    private Double prpo6;
    private Byte prop7;
    private Boolean prpo8;

//    @DateFormat("yyyy-MM-dd HH:mm:ss")
    private Date prpo10;

    private MyEnum prpo11;


}
