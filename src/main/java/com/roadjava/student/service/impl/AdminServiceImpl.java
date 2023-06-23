package com.roadjava.student.service.impl;

import com.roadjava.student.bean.entity.AdminDO;
import com.roadjava.student.bean.res.ResultDTO;
import com.roadjava.student.mapper.AdminMapper;
import com.roadjava.student.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public ResultDTO<AdminDO> validateLogin(AdminDO param) {
        AdminDO adminDO = adminMapper.validateLogin(param.getAdminName());
        if (adminDO == null){
            return ResultDTO.buildFailure("管理员不存在");
        }
        if (!adminDO.getPwd().equals(param.getPwd())){
            return ResultDTO.buildFailure("管理员密码不正确");
        }
        return ResultDTO.buildSuccess(adminDO);
    }
}
