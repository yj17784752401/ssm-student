package com.roadjava.student.service;

import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.StudentDO;
import com.roadjava.student.bean.res.ResultDTO;

import java.util.List;

public interface StudentService {
    ResultDTO<String> addStudent(StudentDTO studentDTO);
    ResultDTO<List<StudentDTO>> listStudentByPage(StudentDTO dto);

    StudentDTO selectOne(StudentDO req);

    ResultDTO<String> updateStudent(StudentDTO dto);


    ResultDTO<String> deleteStudentByIds(List<Long> idsToDelete);
}
