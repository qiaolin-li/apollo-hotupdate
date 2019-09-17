package com.github.qiaolin.apollo.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * 待更新的属性信息
 * @author qiaolin
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyInfo {

    /**
     *  属性对应的key
     */
    private String key;

    /**
     *  属性所在的bean
     */
    private Object target;

    /**
     * 属性所对应的字段
     */
    private Field field;

    /**
     *  默认值
     */
    private String defaultValue;

}
