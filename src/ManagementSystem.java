import java.util.ArrayList;

public class ManagementSystem {
    private ArrayList<Student> studentsList;

    public boolean isStudentExisted(String id) {
        if (studentsList.size() == 0) {
            return false;
        }
        if (studentsList.size() > 0) {
            for (int i = 0; i < studentsList.size(); i++) {
                if (studentsList.get(i).getId().equals(id)) {
                    System.out.println("ID sinh vien da ton tai.");
                    return true;
                }
            }
        }
        return false;
    }

    public void addStudent(Student newStudent) {
        boolean isStudentExisted = isStudentExisted(newStudent.getId());
        if (!isStudentExisted) {
            studentsList.add(newStudent);
        } else {
            System.out.println("Da co hoc sinh nay trong danh sach");
        }
    }
}
