package pers.just1984.framework.web.autoconfigure.component.exception;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import pers.just1984.framework.web.autoconfigure.configuration.property.JustWebMvcProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-03 16:30
 */
@Slf4j
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver, InitializingBean {

    @Autowired
    private JustWebMvcProperties properties;

    @Autowired(required = false)
    private List<ExceptionResolver> exceptionResolvers;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (properties.getGlobalExceptionHandler().isEnableDefaultExceptionHandler()) {
            if (Objects.isNull(exceptionResolvers)) {
                exceptionResolvers = new ArrayList<>();
            }
            exceptionResolvers.add(DefaultExceptionResolver.INSTANCE);
        }
        if (!CollectionUtils.isEmpty(exceptionResolvers)) {
            AnnotationAwareOrderComparator.sort(exceptionResolvers);
        }
        for (ExceptionResolver exceptionResolver : exceptionResolvers) {
            log.info("【just framework component】 '{}' add successful!", exceptionResolver.getClass().getSimpleName());
        }
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        ExceptionResolver exceptionResolver = choose(e);
        Map<String, Object> resMap;
        if (exceptionResolver instanceof DefaultExceptionResolver) {
            DefaultExceptionResolver defaultExceptionResolver = (DefaultExceptionResolver) exceptionResolver;
            resMap = defaultExceptionResolver.resolve(request, response, handler, e);
        } else {
            Object resolved = exceptionResolver.resolve(request, response, handler, e);
            resMap = objToMap(resolved);
        }
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView jsonView = new FastJsonJsonView();
        jsonView.setFastJsonConfig(new FastJsonConfig());
        jsonView.setAttributesMap(resMap);
        mv.setView(jsonView);
        return mv;
    }

    private ExceptionResolver choose(Exception e) {
        return exceptionResolvers.stream().filter(resolver -> resolver.support(e)).findFirst()
                .orElse(DefaultExceptionResolver.INSTANCE);
    }

    private Map<String, Object> objToMap(Object obj) {
        Map<String, Object> map = new HashMap<>(1 << 4);
        BeanMap beanMap = BeanMap.create(obj);
        map.putAll(beanMap);
        return map;
    }

}
