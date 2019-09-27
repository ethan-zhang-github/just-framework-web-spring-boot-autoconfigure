package priv.just1984.framework.web.autoconfigure.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-04 10:15
 */
@Getter
@Setter
@ConfigurationProperties(prefix = JustThreadPoolProperties.PREFIX)
public class JustThreadPoolProperties {

    public static final String PREFIX = "just.thread.pool";

    private boolean enable = false;

    private int corePoolSize = Runtime.getRuntime().availableProcessors();

    private int maxPoolSize = Integer.MAX_VALUE;

    private int queueCapacity = Integer.MAX_VALUE;

    private int keepAliveSeconds = 60;

    private String rejectedExecutionHandler = "java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy";

    private String threadNamePrefix = "Just-TaskPool-";

}
