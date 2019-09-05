package pers.just1984.framework.web.autoconfigure.component.exception;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import pers.just1984.framework.web.autoconfigure.configuration.property.JustWebMvcProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-03 16:30
 */
@Slf4j
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver, InitializingBean, Ordered {

    @Autowired
    private JustWebMvcProperties properties;

    @Autowired(required = false)
    private List<ExceptionResolver> exceptionResolvers;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Objects.isNull(exceptionResolvers)) {
            exceptionResolvers = new ArrayList<>();
        }
        if (properties.getGlobalExceptionHandler().isEnableDefaultExceptionHandler()) {
            exceptionResolvers.add(DefaultExceptionResolver.INSTANCE);
        }
        if (properties.getGlobalExceptionHandler().isEnableBasicExceptionHandler()) {
            exceptionResolvers.add(BasicExceptionResolver.INSTANCE);
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
        if (Objects.isNull(exceptionResolver)) {
            return null;
        }
        Map<String, Object> resMap;
        if (exceptionResolver instanceof DefaultExceptionResolver) {
            DefaultExceptionResolver defaultExceptionResolver = (DefaultExceptionResolver) exceptionResolver;
            resMap = defaultExceptionResolver.resolve(request, response, handler, e);
        } else {
            Object resolved = exceptionResolver.resolve(request, response, handler, e);
            resMap = JSONObject.parseObject(JSONObject.toJSONString(resolved), Map.class);
        }
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView jsonView = new FastJsonJsonView();
        jsonView.setFastJsonConfig(new FastJsonConfig());
        jsonView.setAttributesMap(resMap);
        mv.setView(jsonView);
        return mv;
    }

    private ExceptionResolver choose(Exception e) {
        return exceptionResolvers.stream().filter(resolver -> resolver.support(e)).findFirst().orElse(null);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
