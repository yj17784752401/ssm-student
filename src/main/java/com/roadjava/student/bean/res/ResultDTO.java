package com.roadjava.student.bean.res;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultDTO<T> {
    private String errCode;
    private String errMsg;
    private Boolean success=Boolean.TRUE;
    private  T date;
    private Long total;
    private Map<String,Object> attributes = new HashMap<>();
    private ResultDTO(){

    };
    public void addAttr(String key,Object value){
        this.attributes.put(key, value);
    }
    public static <T> ResultDTO<T> buildSucess(T t,Long total){
        ResultDTO<T> resultDTO= buildSuccess(t);
        resultDTO.setTotal(total);
        return  resultDTO;
    }
    public static <T> ResultDTO<T> buildEmptySuccess(){
        return new ResultDTO<>();
    }
    public static <T> ResultDTO<T> buildSuccess(T t){
        ResultDTO<T> resultDTO =new ResultDTO<>();
        resultDTO.setDate(t);
        return resultDTO;
    }
    public static <T> ResultDTO<T> buildFailure(String code,String errMsg){
        ResultDTO<T> resultDTO =new ResultDTO<>();
        resultDTO.setErrCode(code);
        resultDTO.setErrMsg(errMsg);
        resultDTO.setSuccess(false);
        return  resultDTO;
    }
    public static <T> ResultDTO<T> buildFailure(String errMsg){
        ResultDTO<T> resultDTO =new ResultDTO<>();
        resultDTO.setErrMsg(errMsg);
        resultDTO.setSuccess(false);
        return  resultDTO;
    }
 }
