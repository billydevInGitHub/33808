package billydev.controller;

import billydev.entity.ClazzExtend;
import billydev.entity.Student;
import billydev.mapper.ClazzMapper;
import billydev.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Billy
 */
@RestController
public class SchoolController {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ClazzMapper clazzMapper;

    @GetMapping("/{id}")
    ClazzExtend getAllStudentsByClassId(@PathVariable int id ){
        return clazzMapper.selectWithStudentsById(id);
    }



}
