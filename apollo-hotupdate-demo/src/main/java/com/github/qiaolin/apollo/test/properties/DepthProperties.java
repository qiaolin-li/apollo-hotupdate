package com.github.qiaolin.apollo.test.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 */

@Getter
@Setter
@ConfigurationProperties("qiaolin.hehe.aa")
public class DepthProperties {
    private String a;
    private SecondLevel secondLevel;
    private static final Set<Class<?>> BASE_TYPE = new HashSet<>();;

    static {
        BASE_TYPE.addAll(Arrays.asList(Byte.class, byte.class, Byte[].class, byte.class));
        BASE_TYPE.addAll(Arrays.asList(Character.class, char.class, Character[].class, char.class));
        BASE_TYPE.addAll(Arrays.asList(Short.class, short.class, Short[].class, short.class));
        BASE_TYPE.addAll(Arrays.asList(Integer.class, int.class, Integer[].class, int.class));
        BASE_TYPE.addAll(Arrays.asList(Float.class, float.class, Float[].class, float.class));
        BASE_TYPE.addAll(Arrays.asList(Long.class, long.class, Long[].class, long.class));
        BASE_TYPE.addAll(Arrays.asList(Double.class, double.class, Double[].class, double.class));
        BASE_TYPE.addAll(Arrays.asList(Boolean.class, boolean.class, Boolean[].class, boolean.class));
        BASE_TYPE.addAll(Arrays.asList(String.class, String[].class));
    }

    public static void main(String[] args) {
        recursion(DepthProperties.class, 1);
    }


    private static void recursion(Class<?> clz, int level){
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if(BASE_TYPE.contains(declaredField.getType())){
                System.out.print("|");
                for (int  i = 0; i < level; i++){
                    System.out.print("-");
                }
                System.out.println(declaredField.toString());
            }else{
                recursion(declaredField.getType(), ++level);
            }
        }
    }
}

@Getter
@Setter
class SecondLevel{
    private Integer sa;
    private ThreeLevel threeLevel;
}

@Getter
@Setter
class ThreeLevel{

    private boolean flag;

    private String bbb;

}

