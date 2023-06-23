package com.roadjava.student.service.impl;

import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.StudentDO;
import com.roadjava.student.bean.res.ResultDTO;
import com.roadjava.student.convert.StudentConvert;
import com.roadjava.student.mapper.StudentMapper;
import com.roadjava.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentConvert studentConvert;
    @Override
    public ResultDTO<String> addStudent(StudentDTO studentDTO) {
//        把dto转化为do对象
        StudentDO studentDO = studentConvert.dto2do(studentDTO);
        int result = studentMapper.insert(studentDO);
        if (result==1){
            return ResultDTO.buildSuccess("添加学生成功");
        }else{
            return ResultDTO.buildSuccess("添加学生失败");
        }
    }

    @Override
    public ResultDTO<List<StudentDTO>> listStudentByPage(StudentDTO dto) {
        List<StudentDO> dos = studentMapper.listStudentByPage(dto);
        List<StudentDTO> dtos = studentConvert.dos2dtos(dos);
        Long total = studentMapper.selectCount(dto);
        return ResultDTO.buildSucess(dtos,total);
    }

    @Override
    public StudentDTO selectOne(StudentDO req) {
        StudentDO studentDO =  studentMapper.selectOne(req);
        StudentDTO studentDTO = studentConvert.do2dto(studentDO);
        return studentDTO;
    }

    @Override
    public ResultDTO<String> updateStudent(StudentDTO dto) {
        StudentDO studentDO = studentConvert.dto2do(dto);
        int result = studentMapper.update(studentDO);
        if (result == 1){
            return ResultDTO.buildSuccess("修改学生成功");
        }else {
            return ResultDTO.buildSuccess("修改学生失败");
        }
    }


    @Override
    public ResultDTO<String> deleteStudentByIds(List<Long> idsToDelete) {
        studentMapper.deleteStudentByIds(idsToDelete);
        return ResultDTO.buildSuccess("删除学生成功");
    }
}
