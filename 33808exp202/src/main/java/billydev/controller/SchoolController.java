package billydev.controller;

import billydev.entity.ClazzExtend;
import billydev.entity.Student;
import billydev.mapper.ClazzMapper;
import billydev.mapper.StudentMapper;
import billydev.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Billy
 */
@RestController
public class SchoolController {


    @Autowired
    StudentService studentService;

    @Autowired
    ClazzMapper clazzMapper;

    @GetMapping("/{id}")
    ClazzExtend getAllStudentsByClassId(@PathVariable int id ){
        return clazzMapper.selectWithStudentsById(id);
    }

    @PostMapping("/")
    int updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    


}
