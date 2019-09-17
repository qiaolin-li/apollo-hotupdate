package com.github.qiaolin.apollo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *  apollo 热更新组件自动配置类
 * @author qiaolin
 */

@Configuration
@Import({HotUpdatePropertyConfiguration.class})
public class HotUpdateAutoConfiguration {

}
