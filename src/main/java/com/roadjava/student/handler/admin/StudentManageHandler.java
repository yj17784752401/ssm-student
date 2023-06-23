package com.roadjava.student.handler.admin;

import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.StudentDO;
import com.roadjava.student.bean.res.ResultDTO;
import com.roadjava.student.bean.res.TableResult;
import com.roadjava.student.handler.groups.Add;
import com.roadjava.student.handler.groups.Update;
import com.roadjava.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
@Slf4j
@RequestMapping("admin")
public class StudentManageHandler {
    @Resource
    private StudentService studentService;
//    跳转到学生添加页面
    @GetMapping("/toStudentAdd")
    public String toStudentAdd(){
        return "admin/student/add";
    }

    //    跳转到学生管理页面
    @GetMapping("/toStudentManage")
    public String toStudentManage(HttpServletRequest request) {
        String pageNow = request.getParameter("pageNow");
        if (!StringUtils.hasText(pageNow)){
            request.setAttribute("pageNow",1);
        }else {
            request.setAttribute("pageNow",Integer.parseInt(pageNow));
        }

        return "admin/student/manage";
    }

        /*
        添加学生
         */
    @PostMapping("/addStudent")@ResponseBody
    public ResultDTO<String> addStudent( @Validated(Add.class) StudentDTO dto
                                        ){
        try {
           return studentService.addStudent(dto);
        }catch (Exception e){
            log.error("添加学生异常",e);
            throw new RuntimeException("添加学生异常");
        }
    }

    /*
    查询学生
    */
    @PostMapping("/listStudentByPage")@ResponseBody
    public ResultDTO<TableResult<StudentDTO>> listStudentByPage(StudentDTO dto){
        try {
            TableResult<StudentDTO> tableResult=new TableResult<>();
//            service层返回DTO
            ResultDTO<List<StudentDTO>> resultDTO= studentService.listStudentByPage(dto);
            tableResult.setRows(resultDTO.getDate());
            tableResult.setTotalCount(resultDTO.getTotal());
            System.out.println("tableResult:"+tableResult);
            return ResultDTO.buildSuccess(tableResult);
        }catch (Exception e){
            log.error("查询学生异常",e);
            throw new RuntimeException("查询学生异常");
        }
    }


    //    跳转到学生更新页面
    @GetMapping("/toUpdateStudent")
    public String toUpdateStudent(@RequestParam(value = "id",required = true) Long id ,
                                  @RequestParam("pageNow") Integer pageNow, Model model) {
//        查询出学生信息
        if(id != null){
            StudentDO req = new StudentDO();
            req.setId(id);
            StudentDTO dto = studentService.selectOne(req);
            model.addAttribute("studentToUpdate",dto);
        }
        if (pageNow == null){
            pageNow = 1;
        }
        model.addAttribute("pageNow",pageNow);
        return "admin/student/update";
    }

    /*
      删除学生
       */
    @PostMapping("/deleteStudentByIds")@ResponseBody
    public ResultDTO<String> deleteStudentByIds( @RequestBody StudentDTO dto){
        List<Long> idsToDelete = dto.getIdsToDelete();
        if (idsToDelete == null || idsToDelete.isEmpty()){
            return  ResultDTO.buildFailure("idsToDelete必传");
        }
        try {
            return studentService.deleteStudentByIds( idsToDelete);
        }catch (Exception e){
            log.error("删除学生异常",e);
            throw new RuntimeException("删除学生异常");
        }
    }
    /*
    修改学生
     */
    @PostMapping("/updateStudent")@ResponseBody
    public ResultDTO<String> updateStudent( @Validated(Update.class) StudentDTO dto
    ){
        try {
            System.out.println("查询到的学生"+dto);
            return studentService.updateStudent(dto);
        }catch (Exception e){
            log.error("修改学生异常",e);
            throw new RuntimeException("修改学生异常");
        }
    }
}

