package pers.just1984.framework.web.autoconfigure.component.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import pers.just1984.framework.web.autoconfigure.bean.ResponseBodyEntityBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-03 15:32
 */
@Slf4j
public class DefaultExceptionResolver extends AbstractExceptionResolver implements Ordered {

    public static final DefaultExceptionResolver INSTANCE = new DefaultExceptionResolver();

    private DefaultExceptionResolver() {}

    @Override
    protected Class<? extends Exception>[] initSupport() {
        return new Class[] {JustException.class};
    }

    @Override
    public Map<String, Object> resolve(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        JustException je = JustException.class.cast(e);
        log.error("DefaultExceptionResolver Catch Exception : ", je);
        return ResponseBodyEntityBuilder.error(je.getCode(), je.getMessage()).toMap();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
