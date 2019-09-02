package pers.just1984.framework.web.autoconfigure.component;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 11:39
 */
public class ResponseBodyWrapperFactoryBean implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = returnValueHandlers.stream()
                .filter(handler -> handler instanceof RequestResponseBodyMethodProcessor)
                .map(ResponseBodyWrapperHandler::new).collect(Collectors.toList());
        requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
    }

}
