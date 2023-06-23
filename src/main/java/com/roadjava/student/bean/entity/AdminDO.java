package com.roadjava.student.bean.entity;

import com.roadjava.student.handler.groups.Add;
import com.roadjava.student.handler.groups.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AdminDO {
    private Long id;
    private String adminName;
    private String pwd;
}
