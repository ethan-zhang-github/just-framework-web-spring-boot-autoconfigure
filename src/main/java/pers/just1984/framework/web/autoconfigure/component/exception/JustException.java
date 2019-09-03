package pers.just1984.framework.web.autoconfigure.component.exception;

import lombok.Getter;
import pers.just1984.framework.web.autoconfigure.constant.CommonConstant;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-03 15:33
 */
@Getter
public class JustException extends RuntimeException {

    private int code;

    public JustException() {
        super(CommonConstant.DEFAULT_ERROR_MSG);
        this.code = CommonConstant.DEFAULT_ERROR_CODE;
    }

    public JustException(String msg) {
        super(msg);
        this.code = CommonConstant.DEFAULT_ERROR_CODE;
    }

    public JustException(int code, String msg) {
        super(msg);
        this.code = code;
    }

}
