package com.roadjava.student.util;

import javax.servlet.http.HttpServletRequest;

public class BasePathUtil {
    public static String getBasePath(HttpServletRequest request){
        String basePath = null;
        String scheme =  request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        if (serverPort == 80){
            basePath = scheme + "://" + serverName + contextPath +"/";
        }else {
            basePath = scheme + "://" + serverName + ":" + serverPort + contextPath +"/";
        }
        return basePath;
    }
}
