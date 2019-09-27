package priv.just1984.framework.web.autoconfigure.component;

import priv.just1984.framework.web.autoconfigure.bean.ResponseBodyEntity;
import priv.just1984.framework.web.autoconfigure.bean.ResponseBodyEntityBuilder;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 10:07
 */
@FunctionalInterface
public interface ResponseBodyWrapper extends Wrapper {

    enum DefaultResponseBodyWrapper implements ResponseBodyWrapper {

        INSTANCE;

        @Override
        public Object wrap(Object src) {
            return src instanceof ResponseBodyEntity ? src : ResponseBodyEntityBuilder.success(src);
        }

    }

}
