package com.roadjava.student.handler.student;

import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.StudentDO;
import com.roadjava.student.bean.res.ResultDTO;
import com.roadjava.student.handler.groups.Update;
import com.roadjava.student.service.StudentService;
import com.roadjava.student.util.BasePathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("student")
public class StudentHandler {
    @Resource
    private StudentService studentService;

    private static final String UP_DIR="resources/uploads";

//    学生使用-到更新页面
@GetMapping("/toUpdate")
public String toUpdate(@RequestParam("id") Long id,Model model){
    if (id !=null){
        StudentDO studentDO = new StudentDO();
        studentDO.setId(id);
        StudentDTO studentDTO = studentService.selectOne(studentDO);
        model.addAttribute("dto",studentDTO);
    }
    return "student/update";
}


    //    完成文件上传
    @PostMapping ("/upload")@ResponseBody
    public ResultDTO<String> toUpdate(@RequestParam("file")MultipartFile multipartFile
                            ,HttpServletRequest request){
        if (multipartFile.isEmpty()){
            return ResultDTO.buildFailure("未上传文件或上传的文件内容为空");
        }
        String originalFilename = multipartFile.getOriginalFilename();
        if (!StringUtils.hasText(originalFilename)){
            return ResultDTO.buildFailure("上传的文件名为空");
        }
//获取项目根目录/UP_DIR的绝对路径
        String absUpDir = request.getServletContext().getRealPath("/" + UP_DIR);
//        重命名
        String newFileName = reName(originalFilename);
        String absPathToStore = absUpDir + "/" +newFileName;
        //进行文件存储
        doStoreFile(absPathToStore,multipartFile);
        //获取上传的文件被存储的后的可访问路径
        String addressablePhotoPath = BasePathUtil.getBasePath(request) + UP_DIR + "/" + newFileName;
        return ResultDTO.buildSuccess(addressablePhotoPath);
    }

    private void doStoreFile(String absPathToStore, MultipartFile multipartFile) {
        File destFile =new File(absPathToStore);
        //如果父级目录不存在，就创建
        File parentFile = destFile.getParentFile();
        if (!parentFile.exists()){
            parentFile.mkdirs();
        }
        //执行文件存储
        try {
            multipartFile.transferTo(destFile);
        } catch (IOException e) {
            log.error("存储文件出错",e);
            throw new RuntimeException("存储学生照片文件出错了");
        }
    }



    //获取新的文件名
    private String reName(String originalFilename){
//    获取后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        return System.nanoTime() +suffix;
    }

    /*
    * 修改个人照片
    * */
    @PostMapping("/update") @ResponseBody
    public ResultDTO<String> updateStudent(@Validated(Update.class) StudentDTO dto){
        try {
            return studentService.updateStudent(dto);
        }catch (Exception e){
            log.error("修改学生异常");
            throw new RuntimeException("修改学生异常");
        }
    }
}

