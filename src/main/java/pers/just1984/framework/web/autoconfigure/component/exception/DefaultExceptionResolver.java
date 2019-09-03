package pers.just1984.framework.web.autoconfigure.component.exception;

import org.springframework.core.Ordered;
import pers.just1984.framework.web.autoconfigure.bean.ResponseBodyEntity;
import pers.just1984.framework.web.autoconfigure.bean.ResponseBodyEntityBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-03 15:32
 */
public class DefaultExceptionResolver extends AbstractExceptionResolver implements Ordered {

    public static final DefaultExceptionResolver INSTANCE = new DefaultExceptionResolver();

    private DefaultExceptionResolver() {}

    @Override
    protected Class<? extends Exception>[] initSupport() {
        return new Class[] {JustException.class, Exception.class};
    }

    @Override
    public Map<String, Object> resolve(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        if (e instanceof JustException) {
            JustException je = (JustException) e;
            return genEntity(je).toMap();
        }
        return ResponseBodyEntityBuilder.error().toMap();
    }

    protected ResponseBodyEntity genEntity(JustException je) {
        return ResponseBodyEntityBuilder.error(je.getCode(), je.getMessage());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
