package com.roadjava.student.login.ctx;

import com.roadjava.student.bean.LoginReq;
import com.roadjava.student.bean.res.ResultDTO;
import com.roadjava.student.login.SignIn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SignInCtx {
    @Resource
    private List<SignIn> signIns;
    private Map<String,SignIn> map =new HashMap<>();
    @PostConstruct
    public void  init(){
        signIns.forEach(signIn -> map.put(signIn.getHandleType(),signIn));
    }

    public ResultDTO<String> login(LoginReq loginReq, HttpSession session){
        SignIn signIn = map.get(loginReq.getLoginType());
        if (signIn == null){
            throw new RuntimeException("登录类型不匹配");
        }
        return signIn.login(loginReq,session);
    }
}
