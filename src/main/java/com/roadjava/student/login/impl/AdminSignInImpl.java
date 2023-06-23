package com.roadjava.student.login.impl;

import com.roadjava.student.bean.LoginReq;
import com.roadjava.student.bean.entity.AdminDO;
import com.roadjava.student.bean.res.ResultDTO;
import com.roadjava.student.enums.LoginTypeEnum;
import com.roadjava.student.login.SignIn;
import com.roadjava.student.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/*
* 完成管理员登录功能
* */
@Service

public class AdminSignInImpl implements SignIn {
    @Resource
    private AdminService adminService;
    @Override
    public ResultDTO<String> doLogin(LoginReq loginReq, HttpSession session) {
        String loginName = loginReq.getLoginName();
        String secretCode = loginReq.getSecretCode();
//            管理员登录
            if ((!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode))){
                return ResultDTO.buildFailure("管理员的用户名和密码不能为空");
            }
            //执行校验
            AdminDO param = new AdminDO();
            param.setAdminName(loginName);
            param.setPwd(secretCode);
            ResultDTO<AdminDO> resultDTO = adminService.validateLogin(param);
            if (resultDTO.getSuccess()){
//                校验成功
                session.setAttribute("admin",resultDTO.getDate());
                return ResultDTO.buildSuccess("登录成功");
            }
            return ResultDTO.buildFailure(resultDTO.getErrMsg());
    }

    @Override
    public String getHandleType() {
        return LoginTypeEnum.ADMIN.getType();
    }
}
