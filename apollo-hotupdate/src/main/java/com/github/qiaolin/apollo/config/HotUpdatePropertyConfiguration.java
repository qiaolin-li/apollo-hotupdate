package com.github.qiaolin.apollo.config;

import com.ctrip.framework.apollo.ConfigChangeListener;
import com.github.qiaolin.apollo.listener.ApolloListenerRegister;
import com.github.qiaolin.apollo.listener.ApolloPropertyUpdateListener;
import com.github.qiaolin.apollo.manger.DefaultPropertiesManager;
import com.github.qiaolin.apollo.manger.HotUpdatePropertyManager;
import com.github.qiaolin.apollo.matcher.PropertyKeyMatcher;
import com.github.qiaolin.apollo.processor.HotUpdatePropertyBeanPostProcessor;
import com.github.qiaolin.apollo.properties.HotUpdateProperties;
import com.github.qiaolin.apollo.resolver.ConfigurationPropertiesResolver;
import com.github.qiaolin.apollo.resolver.HotUpdateFieldResolver;
import com.github.qiaolin.apollo.resolver.ValueAnnotationResolver;
import com.github.qiaolin.apollo.validator.DefaultPropertyValidator;
import com.github.qiaolin.apollo.validator.HotPropertyValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 热更新配置相关类
 * @author qiaolin
 */
@Configuration
@EnableConfigurationProperties(HotUpdateProperties.class)
@ComponentScan(basePackages = "com.github.qiaolin.apollo.updater")
public class HotUpdatePropertyConfiguration {

    @Bean
    public ApolloListenerRegister apolloListenerRegister(){
        return new ApolloListenerRegister();
    }

    @Bean
    public ConfigChangeListener apolloPropertyUpdateListener(){
        return new ApolloPropertyUpdateListener();
    }

    @Bean
    public ConfigurationPropertiesResolver configurationPropertiesResolver(){
        return new ConfigurationPropertiesResolver();
    }

    @Bean
    public HotUpdateFieldResolver hotUpdateFieldResolver(){
        return new HotUpdateFieldResolver();
    }

    @Bean
    @ConditionalOnMissingBean
    public HotUpdatePropertyManager defaultPropertiesManager(){
        return new DefaultPropertiesManager();
    }

    @Bean
    public HotUpdatePropertyBeanPostProcessor apolloBeanPostProcessor(){
        return new HotUpdatePropertyBeanPostProcessor();
    }


    @Bean
    public ValueAnnotationResolver valueAnnotationResolver(){
        return new ValueAnnotationResolver();
    }

    @Bean
    public PropertyKeyMatcher propertyKeyMatcher(){
        return new PropertyKeyMatcher();
    }

    @Bean
    public HotPropertyValidator hotPropertyValidator(){
        return new DefaultPropertyValidator();
    }
}
