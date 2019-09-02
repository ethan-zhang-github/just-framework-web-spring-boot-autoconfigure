package pers.just1984.framework.web.autoconfigure.component;

import pers.just1984.framework.web.autoconfigure.bean.ResponseBodyEntity;
import pers.just1984.framework.web.autoconfigure.bean.ResponseBodyEntityBuilder;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 10:07
 */
@FunctionalInterface
public interface ResponseBodyWrapper extends Wrapper {

    class DefaultResponseBodyWrapper implements ResponseBodyWrapper {

        private DefaultResponseBodyWrapper() {}

        public static final DefaultResponseBodyWrapper INSTANCE = new DefaultResponseBodyWrapper();

        @Override
        public Object wrap(Object src) {
            return src instanceof ResponseBodyEntity ? src : ResponseBodyEntityBuilder.success(src);
        }

    }

}
