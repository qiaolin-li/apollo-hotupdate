package com.github.qiaolin.apollo.listener;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.github.qiaolin.apollo.properties.HotUpdateProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


/**
 * apollo 监听器注册
 * @author qiaolin
 */

@Slf4j
public class ApolloListenerRegister implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private HotUpdateProperties hotUpdateProperties;

    @Autowired
    private ConfigChangeListener apolloPropertyUpdateListener;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String[] namespaces = hotUpdateProperties.getNamespaces();
        for (String namespace : namespaces) {
            Config config = ConfigService.getConfig(namespace);
            config.addChangeListener(apolloPropertyUpdateListener::onChange);
        }
    }

}
