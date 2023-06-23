package com.roadjava.student.mapper;

import com.roadjava.student.bean.entity.AdminDO;

public interface AdminMapper {
    AdminDO validateLogin(String loginName);
}
