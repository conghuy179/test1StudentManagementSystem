import java.util.ArrayList;

public class ManagementSystem implements Comparable<Student> {
    private ArrayList<Student> students;

    public ManagementSystem() {
        students = new ArrayList<>();
    }

    public ArrayList<Student> getStudents() {
        return students;
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

    public Student searchStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (id.equals(students.get(i).getId())) {
                return students.get(i);
            }
        }
        return null;
    }

    @Override
    public int compareTo(Student o) {
        return Comparators.NAMEANDID.compare(searchStudent(o.getId()), o);
    }

//    public int compareStu() {
//        Comparator<Student> NAMEANDAGE = new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                int a = o1.getName().compareTo(o2.getName());
//                if (a == 0) {
//                    a = o1.getId() - o2.getId();
//                }
//                return a;
//            }
//        };
//    }


}
