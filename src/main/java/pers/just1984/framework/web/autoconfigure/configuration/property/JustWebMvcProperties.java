package pers.just1984.framework.web.autoconfigure.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 11:27
 */
@Getter
@Setter
@ConfigurationProperties(JustWebMvcProperties.PREFIX)
public class JustWebMvcProperties {

    public static final String PREFIX = "just.web.mvc";

    private boolean enable = true;

    private ResponseBodyWrapProperties responseBodyWrap = new ResponseBodyWrapProperties();

    private GlobalExceptionHandlerProperties globalExceptionHandler = new GlobalExceptionHandlerProperties();

    @Getter
    @Setter
    public static class ResponseBodyWrapProperties {

        public static final String PREFIX = JustWebMvcProperties.PREFIX + ".responseBodyWrap";

        private boolean enable = true;

    }

    @Getter
    @Setter
    public static class GlobalExceptionHandlerProperties {

        public static final String PREFIX = JustWebMvcProperties.PREFIX + ".globalExceptionHandler";

        private boolean enable = true;

        private boolean enableDefaultExceptionHandler = true;

        private boolean enableBasicExceptionHandler = false;

    }

}
