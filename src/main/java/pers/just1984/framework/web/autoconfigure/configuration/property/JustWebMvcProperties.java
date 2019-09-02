package pers.just1984.framework.web.autoconfigure.configuration.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 11:27
 */
@Getter
@ConfigurationProperties(JustWebMvcProperties.PREFIX)
public class JustWebMvcProperties {

    public static final String PREFIX = "just.web.mvc";

    private boolean enable;

    public JustWebMvcProperties() {
        this.enable = true;
    }

}
