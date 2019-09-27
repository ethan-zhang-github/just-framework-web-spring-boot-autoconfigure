package priv.just1984.framework.web.autoconfigure.cache;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 14:19
 */
public interface Cache<T> {

    /**
     * 初始化
     */
    void init();

    /**
     * 清除
     */
    void clear();

    /**
     * 刷新
     */
    void refresh();

}
