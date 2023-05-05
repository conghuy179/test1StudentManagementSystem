package util;

import models.Student;

import java.util.Comparator;

public class Comparators {
    public static Comparator<Student> NAMEANDID = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            int a = o1.getName().compareTo(o2.getName());
            if (a == 0) {
                a = o1.getId().compareTo(o2.getId());
            }
            return a;
        }
    };

}
