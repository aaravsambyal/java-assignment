import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<Student>();
    }

    public boolean addStudent(Student student) {
        if (findStudentById(student.getId()) != null) {
            return false;
        }

        students.add(student);
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public boolean updateStudent(int id, String name, String course, double marks) {
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setCourse(course);
        student.setMarks(marks);
        return true;
    }

    public boolean deleteStudent(int id) {
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        students.remove(student);
        return true;
    }
}
