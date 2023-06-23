package com.roadjava.student.bean;

import lombok.Data;

@Data
public class LoginReq {
    private String loginName;
    private String secretCode;
    private String  loginType;
}
