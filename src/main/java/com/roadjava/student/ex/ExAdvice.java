package com.roadjava.student.ex;

import com.roadjava.student.bean.res.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO<String> handlerEx(Exception ex){
        log.error("统一异常处理：",ex);
        return ResultDTO.buildFailure("系统出现了一些小问题，请联系工作人员");
    }
}
