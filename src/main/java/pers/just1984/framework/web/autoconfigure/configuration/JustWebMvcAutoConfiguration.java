package pers.just1984.framework.web.autoconfigure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.just1984.framework.web.autoconfigure.component.ResponseBodyWrapperFactoryBean;
import pers.just1984.framework.web.autoconfigure.configuration.property.JustWebMvcProperties;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 11:08
 */
@Slf4j
@Configuration
@ConditionalOnBean(WebMvcAutoConfiguration.class)
@ConditionalOnProperty(prefix = "just.web.mvc", name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JustWebMvcProperties.class)
public class JustWebMvcAutoConfiguration {

    @Autowired
    private JustWebMvcProperties justWebMvcProperties;

    @Bean
    @ConditionalOnProperty(prefix = "just.web.mvc.responseBodyWrap", name = "enable", havingValue = "true", matchIfMissing = true)
    public ResponseBodyWrapperFactoryBean responseBodyWrapperFactoryBean() {
        log.info("【just framework component】 'responseBodyWrapperFactoryBean' add successful!");
        return new ResponseBodyWrapperFactoryBean();
    }

}
