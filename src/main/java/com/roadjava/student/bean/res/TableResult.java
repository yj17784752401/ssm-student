package com.roadjava.student.bean.res;

import com.roadjava.student.constants.Constants;
import lombok.Data;

import java.util.List;
@Data
public class TableResult<T> {
    private List<T> rows;
    private Long totalCount;
    public Long getPageCount(){
        long pageCount;
        if (totalCount % Constants.PAGE_SIZE == 0){
            pageCount=totalCount / Constants.PAGE_SIZE;
        }
        else {
            pageCount=totalCount / Constants.PAGE_SIZE + 1;
        }
        return pageCount;
    }
}
