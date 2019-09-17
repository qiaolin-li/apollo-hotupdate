package com.github.qiaolin.apollo.test.config;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.qiaolin.apollo.test.properties.ArrayProperties;
import com.github.qiaolin.apollo.test.properties.DepthProperties;
import com.github.qiaolin.apollo.test.properties.MyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */

@Configuration
@EnableApolloConfig(value = "application", order = 1)
@EnableConfigurationProperties({MyProperties.class, ArrayProperties.class, DepthProperties.class})
//@Import(HotUpdatePropertyConfiguration.class)
public class ApolloConfiguration {

}
