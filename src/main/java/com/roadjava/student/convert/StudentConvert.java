package com.roadjava.student.convert;

import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.StudentDO;
import com.roadjava.student.util.BasePathUtil;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class StudentConvert {

    /*
    * spring里面可以注入当前请求
    * */
    @Resource
    private HttpServletRequest httpServletRequest;

    public abstract StudentDO dto2do(StudentDTO dto);

    /*
     * dto 到 do 转换图片路径
     * */
    @AfterMapping
    protected  void afterDto2Do(StudentDTO dto, @MappingTarget StudentDO studentDO){
       String addressablePhotoPath = dto.getAddressablePhotoPath();
       if (StringUtils.hasText(addressablePhotoPath)){
           String basePath = BasePathUtil.getBasePath(httpServletRequest);
          String photoPath = addressablePhotoPath.substring(basePath.length());
          studentDO.setPhotoPath(photoPath);
       }
    }


    public  abstract StudentDTO do2dto(StudentDO studentDO);

    /*
    * do 到 dto 转换图片路径
    * */
    @AfterMapping
    protected  void afterDo2Dto(StudentDO studentDO, @MappingTarget StudentDTO dto){
        String photoPath = studentDO.getPhotoPath();
        if (StringUtils.hasText(photoPath)){
            String addressablePhotoPath = BasePathUtil.getBasePath(httpServletRequest) + photoPath;
            dto.setAddressablePhotoPath(addressablePhotoPath);
        }
    }

    public  abstract List<StudentDTO> dos2dtos(List<StudentDO> studentDOS);
}
