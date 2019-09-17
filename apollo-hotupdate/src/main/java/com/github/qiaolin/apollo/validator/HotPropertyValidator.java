package com.github.qiaolin.apollo.validator;

/**
 *  热更新属性验证器
 *  <p> 验证其是否需要热更新
 * @author qiaolin
 */

public interface HotPropertyValidator {

    /**
     *  验证属性是否需要热更新
     * @return
     */
    boolean validate(String key);
}
