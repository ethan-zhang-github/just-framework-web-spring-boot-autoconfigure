package priv.just1984.framework.web.autoconfigure.component.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-03 14:58
 */
public interface ExceptionResolver {

    /**
     * 是否支持处理该类异常
     * @param e
     * @return
     */
    boolean support(Exception e);

    /**
     * 处理异常
     * @param request
     * @param response
     * @param handler
     * @param e
     * @return
     */
    Object resolve(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e);

}
