package com.roadjava.student.login.impl;

import com.roadjava.student.bean.LoginReq;
import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.StudentDO;
import com.roadjava.student.bean.res.ResultDTO;
import com.roadjava.student.enums.LoginTypeEnum;
import com.roadjava.student.login.SignIn;
import com.roadjava.student.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
@Service
public class StudentPhoneSignInImpl implements SignIn {
    @Resource
    private StudentService studentService;
    @Override
    public ResultDTO<String> doLogin(LoginReq loginReq, HttpSession session) {
        String loginName = loginReq.getLoginName();
        String secretCode = loginReq.getSecretCode();
        if ((!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode))){
            return ResultDTO.buildFailure("手机号或验证码不能为空");
        }
        //执行校验
        StudentDO param = new StudentDO();
        param.setPhone(loginName);
        StudentDTO dto = studentService.selectOne(param);
        if (dto == null){
            return ResultDTO.buildFailure("手机号不存在");
        }
        if (loginName.equals(secretCode)){
            return ResultDTO.buildFailure("验证码出错");
        }
        session.setAttribute("student",dto);
        return ResultDTO.buildSuccess(String.valueOf(dto.getId()));

    }

    @Override
    public String getHandleType() {
        return LoginTypeEnum.PHONE.getType();
    }
}
