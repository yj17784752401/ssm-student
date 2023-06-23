package com.roadjava.student.handler;

import com.roadjava.student.bean.LoginReq;
import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.AdminDO;
import com.roadjava.student.bean.entity.StudentDO;
import com.roadjava.student.bean.res.ResultDTO;
import com.roadjava.student.enums.LoginTypeEnum;
import com.roadjava.student.login.ctx.SignInCtx;
import com.roadjava.student.service.AdminService;
import com.roadjava.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginHandler {
    @Resource
    private StudentService studentService;
    @Resource
    private AdminService adminService;
    @Resource
    private SignInCtx signInCtx;
//    跳转到登录页面
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    //    退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "login";
    }

    /*
      执行登录
       */
    @PostMapping("/login")@ResponseBody
    public ResultDTO<String> deleteStudentByIds(@RequestBody LoginReq loginReq
            , HttpSession session){
//        判断登录类型是不是在允许范围内
        String loginType = loginReq.getLoginType();
        LoginTypeEnum byType = LoginTypeEnum.getByType(loginType);
        if (byType == null){
            return ResultDTO.buildFailure("登录类型不匹配");
        }
//        通用逻辑
        String loginName = loginReq.getLoginName();
        String secretCode = loginReq.getSecretCode();
        if (LoginTypeEnum.ADMIN.equals(byType)){
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
        }else if (LoginTypeEnum.No.equals(byType)){
            //学号登录
            if ((!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode))){
                return ResultDTO.buildFailure("学号或密码不能为空");
            }
            //执行校验
           StudentDO param = new StudentDO();
            param.setNo(loginName);
            StudentDTO dto = studentService.selectOne(param);
            if (dto == null){
                return ResultDTO.buildFailure("学生不存在");
            }
            if (!dto.getNo().equals(secretCode)){
                 return ResultDTO.buildFailure("密码不存在");
            }
            session.setAttribute("student",dto);
            return ResultDTO.buildSuccess(String.valueOf(dto.getId()));
        }else if (LoginTypeEnum.PHONE.equals(byType)){
//          手机号登录
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
        }else {
//          邮箱登录
            if ((!StringUtils.hasText(loginName) || !StringUtils.hasText(secretCode))){
                return ResultDTO.buildFailure("邮箱或验证码不能为空");
            }
            //执行校验
            StudentDO param = new StudentDO();
            param.setEmail(loginName);
            StudentDTO dto = studentService.selectOne(param);
            if (dto == null){
                return ResultDTO.buildFailure("邮箱不存在");
            }
            if (!loginName.equals(secretCode)){
                return ResultDTO.buildFailure("邮箱验证码不正确");
            }
            session.setAttribute("student",dto);
            return ResultDTO.buildSuccess(String.valueOf(dto.getId()));
        }
    }


    /*
     执行登录--可扩展实现
      */
    @PostMapping("/login2")@ResponseBody
    public ResultDTO<String> login2(@RequestBody LoginReq loginReq
            , HttpSession session){
         return  signInCtx.login(loginReq,session);
    }
}

