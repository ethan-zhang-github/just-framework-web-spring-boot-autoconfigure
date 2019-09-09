package pers.just1984.framework.web.autoconfigure.component.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-06 11:49
 */
@Endpoint(id = JustThreadPoolEndpoint.ID)
public class JustThreadPoolEndpoint {

    public static final String ID = "just-thread-pool";

    @Autowired
    private Executor executor;

    @ReadOperation
    public Map<String, Object> invoke() {
        Map<String, Object> map = new LinkedHashMap<>(1 << 4);
        if (executor instanceof ThreadPoolTaskExecutor) {
            ThreadPoolTaskExecutor threadPoolTaskExecutor = ThreadPoolTaskExecutor.class.cast(executor);
            map.put("poolSize", threadPoolTaskExecutor.getPoolSize());
            map.put("corePoolSize", threadPoolTaskExecutor.getCorePoolSize());
            map.put("queueSize", threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().size());
            map.put("maxPoolSize", threadPoolTaskExecutor.getMaxPoolSize());
            map.put("activeCount", threadPoolTaskExecutor.getActiveCount());
            map.put("completedTaskCount", threadPoolTaskExecutor.getThreadPoolExecutor().getCompletedTaskCount());
            map.put("taskCount", threadPoolTaskExecutor.getThreadPoolExecutor().getTaskCount());
            map.put("isTerminated", threadPoolTaskExecutor.getThreadPoolExecutor().isTerminated());
        }
        return map;
    }

}
