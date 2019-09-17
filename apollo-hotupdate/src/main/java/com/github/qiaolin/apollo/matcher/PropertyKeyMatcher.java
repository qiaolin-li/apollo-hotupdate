package com.github.qiaolin.apollo.matcher;

import org.springframework.util.AntPathMatcher;

/**
 *  属性匹配器
 * @author qiaolin
 */

public class PropertyKeyMatcher extends AntPathMatcher {
    /**
     *  默认的分隔符
     */
    public static final String DEFAULT_PATH_SEPARATOR = ".";

    public PropertyKeyMatcher(){
        super(DEFAULT_PATH_SEPARATOR);
    }

    /**
     *  匹配任意一个表达式即可
     * @param patterns
     * @param key
     * @return
     */
    public boolean anyMatch(String[] patterns, String key){
        for (String pattern : patterns) {
            if(match(pattern, key)){
                return true;
            }
        }
        return false;
    }
}
