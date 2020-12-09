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

    @GetMapping("/class/{id}")
    ClazzExtend getAllStudentsByClassId(@PathVariable int id ){
        return clazzMapper.selectWithStudentsById(id);
    }

    @PostMapping("/student")
    int insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }

    @GetMapping("/student/{id}")
    Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id).orElseThrow(()->new RuntimeException("Student Not found Exception"));
    }

    @PutMapping("/student/{id}")
    int updateStudent(@RequestBody Student student, @PathVariable int id) {
        // usually student only has fields other than id
        if(id!=student.getId().intValue()){
            student.setId(id);
        }
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/student/{id}")
    void deleteStudent(@PathVariable int id) {
        studentService.deleteStudentById(id);
    }
}
