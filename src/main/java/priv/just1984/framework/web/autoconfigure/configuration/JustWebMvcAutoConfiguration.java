package priv.just1984.framework.web.autoconfigure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import priv.just1984.framework.web.autoconfigure.component.ResponseBodyWrapperFactoryBean;
import priv.just1984.framework.web.autoconfigure.component.exception.GlobalHandlerExceptionResolver;
import priv.just1984.framework.web.autoconfigure.configuration.property.JustWebMvcProperties;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 11:08
 */
@Slf4j
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = JustWebMvcProperties.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JustWebMvcProperties.class)
@Configuration
public class JustWebMvcAutoConfiguration {

    @Autowired
    private JustWebMvcProperties properties;

    @Bean
    @ConditionalOnProperty(prefix = JustWebMvcProperties.ResponseBodyWrapProperties.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
    public ResponseBodyWrapperFactoryBean responseBodyWrapperFactoryBean() {
        log.info("【just framework component】 'responseBodyWrapperFactoryBean' add successful!");
        return new ResponseBodyWrapperFactoryBean();
    }

    @Bean
    @ConditionalOnProperty(prefix = JustWebMvcProperties.GlobalExceptionHandlerProperties.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
    public HandlerExceptionResolver globalHandlerExceptionResolver() {
        log.info("【just framework component】 'globalHandlerExceptionResolver' add successful!");
        return new GlobalHandlerExceptionResolver();
    }

}
