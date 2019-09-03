package pers.just1984.framework.web.autoconfigure.bean;

import pers.just1984.framework.web.autoconfigure.constant.CommonConstant;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 10:15
 */
public class ResponseBodyEntityBuilder {

    public static ResponseBodyEntity success() {
        return success(null);
    }

    public static ResponseBodyEntity success(Object data) {
        return generate(CommonConstant.DEFAULT_SUCCESS_CODE, CommonConstant.DEFAULT_SUCCESS_MSG, data);
    }

    public static ResponseBodyEntity error() {
        return error(CommonConstant.DEFAULT_ERROR_MSG);
    }

    public static ResponseBodyEntity error(String msg) {
        return error(CommonConstant.DEFAULT_ERROR_CODE, msg);
    }

    public static ResponseBodyEntity error(int code, String msg) {
        return generate(code, msg, null);
    }

    public static ResponseBodyEntity generate(int code, String msg, Object data) {
        return new ResponseBodyEntity(code, msg, data);
    }

}
