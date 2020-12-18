package billydev.service;

import billydev.entity.Student;
import billydev.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Billy
 */
@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public  Optional<Student> getStudentById(int id) {
        return Optional.ofNullable(studentMapper.findStudentById(id));
    }

    public List<Student> findAllStudents() {
        return studentMapper.findAll();
    }


    public int deleteStudentById(int id) {
        return studentMapper.deleteStudentById(id);
    }

    public int insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Transactional
    public int insertStudents(List<Student> students) {
        for (Student student : students) {
            studentMapper.insertStudent(student);
        }
        return students.size();
    }

}
