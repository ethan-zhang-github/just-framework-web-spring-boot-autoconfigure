package priv.just1984.framework.web.autoconfigure.component;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 10:07
 */
@FunctionalInterface
public interface Wrapper {

    /**
     * 包装
     * @param src
     * @return
     */
    Object wrap(Object src);

}
