package pers.just1984.framework.web.autoconfigure.bean;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 10:15
 */
public class ResponseBodyEntityBuilder {

    private static final int DEFAULT_SUCCESS_CODE = 200;

    private static final String DEFAULT_SUCCESS_MSG = "成功";

    public static ResponseBodyEntity success() {
        return success(null);
    }

    public static ResponseBodyEntity success(Object data) {
        return generate(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG, data);
    }

    public static ResponseBodyEntity generate(int code, String msg, Object data) {
        return new ResponseBodyEntity(code, msg, data);
    }

}
