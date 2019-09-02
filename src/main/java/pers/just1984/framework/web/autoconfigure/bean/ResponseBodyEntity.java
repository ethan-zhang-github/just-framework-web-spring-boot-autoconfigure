package pers.just1984.framework.web.autoconfigure.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author: zhangyifan@wshifu.com
 * @date: 2019-09-02 10:13
 */
@Data
@AllArgsConstructor
public class ResponseBodyEntity {

    private int code;

    private String msg;

    private Object data;

}
