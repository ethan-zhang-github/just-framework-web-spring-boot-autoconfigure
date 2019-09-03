package pers.just1984.framework.web.autoconfigure.component.exception;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-03 15:04
 */
public abstract class AbstractExceptionResolver implements ExceptionResolver {

    private Set<Class<? extends Exception>> supportExceptions;

    public AbstractExceptionResolver() {
        this.supportExceptions = new HashSet<>(Arrays.asList(initSupport()));
    }

    @Override
    public boolean support(Exception e) {
        return supportExceptions.stream().anyMatch(clz -> clz.isInstance(e));
    }

    public void addSupport(Class<? extends Exception> clz) {
        supportExceptions.add(clz);
    }

    public void cancelSupport(Class<? extends Exception> clz) {
        supportExceptions.remove(clz);
    }

    /**
     * 初始化支持类型
     * @return
     */
    protected abstract Class<? extends Exception>[] initSupport();

}
