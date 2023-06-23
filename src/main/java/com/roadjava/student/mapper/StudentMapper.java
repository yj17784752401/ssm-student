package com.roadjava.student.mapper;

import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.StudentDO;

import java.util.List;

public interface StudentMapper {

    int insert(StudentDO studentDO);

    List<StudentDO> listStudentByPage(StudentDTO dto);

    long selectCount(StudentDTO dto);

    StudentDO selectOne(StudentDO req);

    int update(StudentDO studentDO);

    void deleteStudentByIds(List<Long> idsToDelete);
}
