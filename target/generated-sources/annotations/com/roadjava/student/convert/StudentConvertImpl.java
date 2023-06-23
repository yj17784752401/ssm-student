package com.roadjava.student.convert;

import com.roadjava.student.bean.dto.StudentDTO;
import com.roadjava.student.bean.entity.StudentDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-23T19:31:26+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class StudentConvertImpl extends StudentConvert {

    @Override
    public StudentDO dto2do(StudentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        StudentDO studentDO = new StudentDO();

        studentDO.setId( dto.getId() );
        studentDO.setNo( dto.getNo() );
        studentDO.setRealName( dto.getRealName() );
        studentDO.setBirthDay( dto.getBirthDay() );
        studentDO.setPhone( dto.getPhone() );
        studentDO.setEmail( dto.getEmail() );
        studentDO.setPhotoPath( dto.getPhotoPath() );

        afterDto2Do( dto, studentDO );

        return studentDO;
    }

    @Override
    public StudentDTO do2dto(StudentDO studentDO) {
        if ( studentDO == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId( studentDO.getId() );
        studentDTO.setNo( studentDO.getNo() );
        studentDTO.setRealName( studentDO.getRealName() );
        studentDTO.setBirthDay( studentDO.getBirthDay() );
        studentDTO.setPhone( studentDO.getPhone() );
        studentDTO.setEmail( studentDO.getEmail() );
        studentDTO.setPhotoPath( studentDO.getPhotoPath() );

        afterDo2Dto( studentDO, studentDTO );

        return studentDTO;
    }

    @Override
    public List<StudentDTO> dos2dtos(List<StudentDO> studentDOS) {
        if ( studentDOS == null ) {
            return null;
        }

        List<StudentDTO> list = new ArrayList<StudentDTO>( studentDOS.size() );
        for ( StudentDO studentDO : studentDOS ) {
            list.add( do2dto( studentDO ) );
        }

        return list;
    }
}
