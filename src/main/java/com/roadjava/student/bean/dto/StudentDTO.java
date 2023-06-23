package com.roadjava.student.bean.dto;

import com.roadjava.student.bean.entity.StudentDO;
import com.roadjava.student.constants.Constants;
import lombok.Data;

import java.util.List;

@Data
public class StudentDTO extends StudentDO {
    private Integer pageNow;

    private Integer pageSize;

    /*
    * 可访问的图片路径
    * */
    private String addressablePhotoPath;

    private List<Long> idsToDelete;
    public Integer getStart(){
        if (getPageNow() == null){
            return null;
        }
        return (getPageNow()-1) * getPageSize();
    }

    public Integer getPageSize() {

        return pageSize != null ? pageSize : Constants.PAGE_SIZE;
    }
}
