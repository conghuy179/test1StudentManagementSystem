import java.util.ArrayList;

public class ManagementSystem {
    private ArrayList<Student> students;

    public ManagementSystem() {
        students = new ArrayList<>();
    }

    public int getNumStudents() {
        return students.size();
    }

    public boolean isStudentExisted(String id) {
        boolean existed = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                System.out.println("ID sinh vien da ton tai.");
                existed = true;
                break;
            }
        }
        return existed;
    }

    public void addStudent(Student newStudent) {
        boolean isStudentExisted = isStudentExisted(newStudent.getId());
        if (!isStudentExisted) {
            students.add(newStudent);
        } else {
            System.out.println("Da co hoc sinh nay trong danh sach");
        }
    }
}
