package com.github.qiaolin.apollo.test.properties;


import com.github.qiaolin.apollo.test.enumerate.MyEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "com.github.qiaolin.array")
public class ArrayProperties {

    private Integer[] prpo1;
    private String[] prpo2;
    private Long[]  prpo3;
    private Short[] prpo4;
    private Float[] prpo5;
    private Double[] prpo6;
    private Byte[] prop7;
    private Boolean[] prpo8;
    private String[] prpo9;
    private MyEnum[] prpo10;


}
