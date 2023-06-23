package com.roadjava.student.interceptor;

import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.AdminDO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StudentDTO studentDTO =(StudentDTO) request.getSession().getAttribute("student");
        if (studentDTO == null){
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            return false;
        }
        return true;
    }
}
