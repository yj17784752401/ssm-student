package com.roadjava.student.bean.entity;

import com.roadjava.student.handler.groups.Add;
import com.roadjava.student.handler.groups.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentDO {
    @NotNull(groups = {Update.class})
    private Long id;
    @NotBlank(groups = {Add.class, Update.class})
    private String no;
    @NotBlank(groups = {Add.class, Update.class})
    private String realName;
    @NotBlank(groups = {Add.class, Update.class})
    private String birthDay;
    @NotBlank(groups = {Add.class, Update.class})
    private String phone;
    @NotBlank(groups = {Add.class, Update.class})
    private String email;
    /*
    * 存储格式：resource/imgs/plus.png
    * */
    private String photoPath;
}
