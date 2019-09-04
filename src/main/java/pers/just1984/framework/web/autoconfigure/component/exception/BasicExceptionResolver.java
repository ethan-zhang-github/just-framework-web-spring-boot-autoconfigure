package pers.just1984.framework.web.autoconfigure.component.exception;

import org.springframework.core.Ordered;
import pers.just1984.framework.web.autoconfigure.bean.ResponseBodyEntityBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-04 9:56
 */
public class BasicExceptionResolver extends AbstractExceptionResolver implements Ordered {

    public static final BasicExceptionResolver INSTANCE = new BasicExceptionResolver();

    private BasicExceptionResolver() {}

    @Override
    protected Class<? extends Exception>[] initSupport() {
        return new Class[] {Exception.class};
    }

    @Override
    public Map<String, Object> resolve(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        return ResponseBodyEntityBuilder.error(e.getMessage()).toMap();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

}
