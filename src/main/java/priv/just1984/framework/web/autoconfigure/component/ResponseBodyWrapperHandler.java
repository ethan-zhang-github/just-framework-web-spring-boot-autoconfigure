package priv.just1984.framework.web.autoconfigure.component;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import priv.just1984.framework.web.autoconfigure.annotation.ResponseBodyWrap;
import priv.just1984.framework.web.autoconfigure.cache.ResponseBodyWrapperCache;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 10:04
 */
public class ResponseBodyWrapperHandler implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler delegate;

    private ResponseBodyWrapperCache cache;

    public ResponseBodyWrapperHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
        this.cache = new ResponseBodyWrapperCache();
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (returnType.hasMethodAnnotation(ResponseBodyWrap.class)) {
            ResponseBodyWrap responseBodyWrap = returnType.getMethodAnnotation(ResponseBodyWrap.class);
            Class<? extends ResponseBodyWrapper> responseBodyWrapperClazz = responseBodyWrap.value();
            if (responseBodyWrapperClazz == ResponseBodyWrapper.DefaultResponseBodyWrapper.class) {
                returnValue = ResponseBodyWrapper.DefaultResponseBodyWrapper.INSTANCE.wrap(returnValue);
            } else {
                ResponseBodyWrapper specialWrapperInstance = getSpecialWrapperInstance(responseBodyWrapperClazz);
                returnValue = specialWrapperInstance.wrap(returnValue);
            }
        }
        this.delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

    private ResponseBodyWrapper getSpecialWrapperInstance(Class<? extends ResponseBodyWrapper> targetClazz)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (cache.contains(targetClazz)) {
            return cache.get(targetClazz);
        }
        Constructor<? extends ResponseBodyWrapper> constructor = targetClazz.getDeclaredConstructor();
        ResponseBodyWrapper responseBodyWrapper = constructor.newInstance();
        cache.put(targetClazz, responseBodyWrapper);
        return responseBodyWrapper;
    }

}
