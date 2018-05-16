package com.bici.tsdb.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ResultDTO
 * @author: Overload
 * @date: 2018/5/15 11:44
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO implements Serializable {

    private final static Integer SUCCESS_CODE = 100;

    private final static String SUCCESS_MSG = "success";

    private Integer code;

    private String msg;

    private Object data;

    public static ResultDTO success(Object data) {
        return new ResultDTO(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static ResultDTO success() {
        return success(null);
    }

    public static ResultDTO fail(Integer code, String msg, Object data) {
        return new ResultDTO(code, msg, data);
    }

    public static ResultDTO fail(Integer code, String msg) {
        return fail(code, msg, null);
    }
}
