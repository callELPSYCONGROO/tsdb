package com.bici.tsdb.query.handler;

import com.bici.tsdb.common.entity.ResultData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionHandler统一异常处理
 * @author: bici
 * @date: 2018/4/16 10:26
 * @version: 1.0
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultData handler(Exception e) {
        e.printStackTrace();
        return ResultData.fail(100, e.getMessage());
    }
}
