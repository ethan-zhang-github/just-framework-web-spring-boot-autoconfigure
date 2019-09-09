package pers.just1984.framework.web.autoconfigure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pers.just1984.framework.web.autoconfigure.component.endpoint.JustThreadPoolEndpoint;
import pers.just1984.framework.web.autoconfigure.configuration.property.JustThreadPoolProperties;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-04 10:15
 */
@Slf4j
@ConditionalOnProperty(prefix = JustThreadPoolProperties.PREFIX, name = "enable", havingValue = "true")
@EnableConfigurationProperties(JustThreadPoolProperties.class)
@EnableAsync
@Configuration
public class JustThreadPoolConfiguration {

    @Autowired
    private JustThreadPoolProperties properties;

    private ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    @Bean
    public Executor executor() {
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        try {
            executor.setRejectedExecutionHandler((RejectedExecutionHandler) Class.forName(properties.getRejectedExecutionHandler()).newInstance());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.error("【just framework component】 'rejectedExecutionHandler' add error!", e);
        }
        log.info("【just framework component】 'executor' add successful!");
        return executor;
    }

    @Bean
    public AsyncConfigurer asyncConfigurer(Executor executor) {
        return new AsyncConfigurer() {
            @Override
            public Executor getAsyncExecutor() {
                return executor;
            }
        };
    }

    @ConditionalOnClass(Health.class)
    @ConditionalOnEnabledEndpoint
    @Bean
    public JustThreadPoolEndpoint justThreadPoolEndpoint() {
        return new JustThreadPoolEndpoint();
    }

}
