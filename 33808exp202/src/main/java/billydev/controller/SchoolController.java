package billydev.controller;

import billydev.entity.ClazzExtend;
import billydev.entity.Student;
import billydev.mapper.ClazzMapper;
import billydev.mapper.StudentMapper;
import billydev.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Billy
 */
@Slf4j
@RestController
@Validated
public class SchoolController {

    @Autowired
    StudentService studentService;

    @Autowired
    ClazzMapper clazzMapper;

    @GetMapping(value="/")
    ClazzExtend getAllStudentsByClassIdDefault(){
        log.debug("request of getAllStudentsByClassId executed with class id:{}", 1);
        return clazzMapper.selectWithStudentsById(1);
    }

    @GetMapping(value={"/class/{id}"})
    ClazzExtend getAllStudentsByClassId(@PathVariable(name = "id", required = false) int id ){
        log.debug("request of getAllStudentsByClassId executed with class id:{}", id);
        return clazzMapper.selectWithStudentsById(id);
    }

    @PostMapping("/student")
    ResponseEntity<Integer> insertStudent(@Valid @RequestBody Student student) {
        int rowNumInserted=studentService.insertStudent(student);
        log.info("{} row(s) inserted for student entity", rowNumInserted);
        return new ResponseEntity(rowNumInserted, HttpStatus.CREATED);
    }

    @PostMapping("/students")
    ResponseEntity<Integer> insertStudents(@Valid @RequestBody List<Student> students) {
        int rowNumInserted=studentService.insertStudents(students);
        log.info("{} row(s) inserted for student entity", rowNumInserted);
        return new ResponseEntity(rowNumInserted, HttpStatus.CREATED);
    }

    @GetMapping("/student/{id}")
    Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id).orElseThrow(()->new RuntimeException("Student Not found Exception"));
    }

    @PutMapping("/student/{id}")
    int updateStudent(@Valid @RequestBody Student student, @PathVariable int id) {
        // usually student only has fields other than id
        if(id!=student.getId().intValue()){
            student.setId(id);
        }
        int rowNumberUpdated=studentService.updateStudent(student);
        log.info("{} row(s) updated with student id:{}", rowNumberUpdated, student.getId());
        return rowNumberUpdated;
    }

    @DeleteMapping("/student/{id}")
    void deleteStudent(@PathVariable int id) {
        studentService.deleteStudentById(id);
        log.info("student with id :{} deleted", id);
    }
}
