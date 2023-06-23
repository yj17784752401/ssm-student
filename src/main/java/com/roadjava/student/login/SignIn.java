package com.roadjava.student.login;

import com.roadjava.student.bean.LoginReq;
import com.roadjava.student.bean.res.ResultDTO;

import javax.servlet.http.HttpSession;

public interface SignIn {
    default ResultDTO<String> login(LoginReq loginReq, HttpSession session){
        //通用逻辑
        return doLogin(loginReq,session);
    }
/*
* 之类实现自己的业务逻辑
* */
    ResultDTO<String> doLogin(LoginReq loginReq, HttpSession session);
    /*
    * 获取之类处理的登录类型
    * */
     String getHandleType();
}
