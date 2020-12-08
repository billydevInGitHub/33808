package billydev.mapper;

import billydev.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Billy
 */
public interface StudentMapper {
    /**
     * find all students.
     * @return student list.
     */
    List<Student> findAll();

    /**
     * find student by student id.
     * @param id
     * @return student instance with matched id.
     */
    Student findStudentById(@Param("id") int id);

    /**
     * update student with provided information.
     * @param student
     * @return number of student updated.
     */
    int updateStudent(@Param("student") Student student);

    /**
     * insert a new student with provided student information.
     * @param student
     * @return number of student created.
     */
    int insertStudent(@Param("student") Student student);

    /**
     * delete student of certain id.
     * @param id
     * @return number of students deleted.
     */
    int deleteStudentById(@Param("id") int id);
}
