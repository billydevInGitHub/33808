package billydev.entity;

import java.util.List;

public class ClazzExtend extends Clazz{
    List<Student> students;

    public List<Student> getStudentList() {
        return students;
    }

    public void setStudentList(List<Student> students) {
        this.students = students;
    }
}
