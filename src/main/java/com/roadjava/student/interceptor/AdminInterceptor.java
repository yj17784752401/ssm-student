package com.roadjava.student.interceptor;

import com.roadjava.student.bean.entity.AdminDO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AdminDO admin =(AdminDO) request.getSession().getAttribute("admin");
        if (admin == null){
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            return false;
        }
        return true;
    }
}
