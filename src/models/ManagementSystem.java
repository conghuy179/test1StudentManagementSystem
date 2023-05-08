package models;

import java.util.ArrayList;

public class ManagementSystem {
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
                existed = true;
                break;
            }
        }
        return existed;
    }

    public void addStudent(Student newStudent) {
        if (newStudent == null) {
            System.out.println("Khong the them hoc sinh nay.");
        }
        boolean isStudentExisted = isStudentExisted(newStudent.getId());
        if (!isStudentExisted) {
            students.add(newStudent);
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

    public void deleteStudent(String id) {
        int foundIndex = -1;
        if (searchStudent(id) == null) {
            System.out.println("Khong tim thay sinh vien.");
        } else {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId().equals(id)) {
                    foundIndex = i;
                }
            }
            students.remove(foundIndex);
        }
    }

    public Student searchStudentLastName(String lastName) {
        for (int i = 0; i < students.size(); i++) {
            if (lastName.equals(students.get(i).getLastName())) {
                return students.get(i);
            }
        }
        return null;
    }

    public ArrayList<Student> searchSortStudent(String name) {
        ArrayList<Student> results = new ArrayList<Student>();
        String nameLowerCase = name.toLowerCase();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getLastName().toLowerCase().contains(nameLowerCase)) {
                results.add(students.get(i));
            }
        }
        return results;
    }
}
