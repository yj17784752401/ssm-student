package com.roadjava.student.service;

import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.AdminDO;
import com.roadjava.student.bean.entity.StudentDO;
import com.roadjava.student.bean.res.ResultDTO;

import java.util.List;

public interface AdminService {
  ResultDTO<AdminDO> validateLogin(AdminDO param);
}
