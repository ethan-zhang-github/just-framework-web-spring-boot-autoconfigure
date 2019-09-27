package priv.just1984.framework.web.autoconfigure.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(1 << 2);
        map.put("code", this.getCode());
        map.put("msg", this.getMsg());
        map.put("data", this.getData());
        return map;
    }

}
