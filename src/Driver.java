import models.ManagementSystem;
import models.Student;
import util.Comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {
    public static final int CHOICE_CREATE = 1;
    public static final int CHOICE_SEARCH_SORT = 2;
    public static final int CHOICE_UPDATE_DELETE = 3;
    public static final int CHOICE_REPORT = 4;
    public static final int CHOICE_EXIT = 5;

    public static final int MINIMUM_STUDENT_CREATED = 10;
    private int selection;
    private Scanner sc;
    private ManagementSystem ms;

    public Driver(ManagementSystem ms, Scanner sc) {
        this.ms = ms;
        this.sc = sc.useDelimiter("\n");
    }

    public void printMenu() {
        System.out.println("CHAO MUNG DEN QUAN LY SINH VIEN");
        System.out.println("1. TAO");
        System.out.println("2. TIM KIEM VA SAP XEP");
        System.out.println("3. CAP NHAT/XOA");
        System.out.println("4. BAO CAO");
        System.out.println("5. THOAT");
        System.out.println("Nhap lua chon: ");
    }


    public void run() {
        String selectionText;
        do {
            printMenu();
            try {
                selectionText = sc.next();
                isValidSelection(selectionText);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            selection = Integer.parseInt(selectionText);
            if (selection == CHOICE_CREATE) {
                runCreate();
            } else if (selection == CHOICE_SEARCH_SORT) {
                runSearchSort();
            } else if (selection == CHOICE_UPDATE_DELETE) {
                runUpdateDelete();
            } else if (selection == CHOICE_REPORT) {
                runReport();
            }
        } while (selection != CHOICE_EXIT);
    }

    public static boolean isValidSelection(String selection) {
        boolean isDigit = false;
        if (selection.length() > 1) {
            throw new IllegalArgumentException("Chi nhap 1 so tu 1 den 5.");
        } else if (selection.charAt(0) < '1' || selection.charAt(0) > '5') {
            throw new IllegalArgumentException("Chi nhap 1 so trong khoang tu 1 den 5");
        } else {
            isDigit = true;
        }
        return isDigit;
    }

    public void runCreate() {
        System.out.println("Ban da chon: Tao sinh vien");
        System.out.println("Nhap thong tin sinh vien");
        char continueOrNot;
        do {
            createNewStudent();
        } while (ms.getNumStudents() < MINIMUM_STUDENT_CREATED);

        do {
            System.out.println("Ban co muon tao sinh vien tiep (Y/N) khong? ");
            System.out.println("Chon Y de tiep tuc, N de quay lai man hinh chinh");
            continueOrNot = sc.next().charAt(0);
            if (continueOrNot == 'Y' || continueOrNot == 'y') {
                createNewStudent();
            } else if (continueOrNot != 'N' && continueOrNot != 'n') {
                System.out.println("Lua chon khong hop le");
            }
        } while (continueOrNot != 'N' && continueOrNot != 'n');
    }

    public void createNewStudent() {
        String studentId;
        String studentFamilyName;
        String studentLastName;
        String studentYear;
        String studentCourse;

        do {
            System.out.println("Nhap ID sinh vien: ");
            studentId = sc.next();
            if (!isIdValid(studentId)) {
                System.out.println("ID co do dai 5 chu so. Yeu cau nhap lai.");
            } else if (ms.isStudentExisted(studentId)) {
                System.out.println("Sinh vien da ton tai. Yeu cau nhap lai.");
            }
        } while (!isIdValid(studentId) || ms.isStudentExisted(studentId));

        do {
            System.out.println("Nhap ho cua sinh vien: ");
            studentFamilyName = sc.next();
            if (!isNameValid(studentFamilyName)) {
                System.out.println("Ho gioi han 20 chu va khong co so. Yeu cau nhap lai.");
            }
            System.out.println("Nhap ten cua sinh vien: ");
            studentLastName = sc.next();
            if (!isNameValid(studentLastName)) {
                System.out.println("Ten gioi han 20 chu va khong co so. Yeu cau nhap lai.");
            }
        } while (!isNameValid(studentFamilyName) && !isNameValid(studentLastName));

        int studentYearAfterValidation = -1;
        do {
            System.out.println("Nhap so hoc ky (1 hoac 2): ");
            try {
                studentYear = sc.next();
                isYearValid(studentYear);
            } catch (IllegalArgumentException e) {
                System.out.println("Chi nhap hoc ky 1 hoac 2. Yeu cau nhap lai.");
                continue;
            }

            studentYearAfterValidation = Integer.parseInt(studentYear);
            if (studentYearAfterValidation != 1 && studentYearAfterValidation != 2) {
                System.out.println("So hoc ky phai la 1 hoac 2. Yeu cau nhap lai.");
            }
        } while (studentYearAfterValidation != 1 && studentYearAfterValidation != 2);

        do {
            System.out.println("Nhap ten khoa hoc (Java, .Net, C / C++): ");
            studentCourse = sc.next();
            studentCourse = convertCourse(studentCourse);
            if (!isCourseValid(studentCourse)) {
                System.out.println("Ten khoa hoc phai thuoc 1 trong 4 khoa hoc (Java, .Net, C / C++).");
                System.out.println("Yeu cau nhap lai.");
            }
        } while (!isCourseValid(studentCourse));

        ms.addStudent(new Student(studentId, studentFamilyName, studentLastName, studentYearAfterValidation, studentCourse));

    }

    public boolean isIdValid(String id) {
        boolean result = true;
        if (id.length() != 5) {
            return false;
        }
        for (int i = 0; i < 5; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                result = false;
                break;
            }
        }
        return result;
    }
    public boolean isNameValid(String name) {
        boolean result = true;
        if (name.length() > 20) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isYearValid(String year) {
        boolean isDigit = false;
        if (year.charAt(0) != '1' && year.charAt(0) != '2') {
            throw new IllegalArgumentException("Chi nhap 1 hoac 2");
        } else {
            isDigit = true;
        }
        return isDigit;
    }

    public String convertCourse(String course) {
        String correctCourseName = null;
        if (course.equals("java")) {
            correctCourseName = "Java";
        } else if (course.equals(".net")) {
            correctCourseName = ".Net";
        } else if (course.equals("c")) {
            correctCourseName = "C";
        } else if (course.equals("c++")) {
            correctCourseName = "C++";
        } else if (course.equals("Java") ||
                course.equals(".Net") ||
                course.equals("C") ||
                course.equals("C++")) {
            return course;
        }
        return correctCourseName;
    }
    public boolean isCourseValid(String course) {
        boolean result = true;
        if (course.equals("java")) {
            course = "Java";
        } else if (course.equals(".net")) {
            course = ".Net";
        } else if (course.equals("c")) {
            course = "C";
        } else if (course.equals("c++")) {
            course = "C++";
        }

        if (!course.equals("Java") &&
                !course.equals(".Net") &&
                !course.equals("C") &&
                !course.equals("C++")) {
            result = false;
        }
        return result;
    }

    private void runSearchSort() {

        System.out.println("Nhap ten sinh vien can tim: ");
        String studentLastName = sc.next();
        ArrayList<Student> results = ms.searchSortStudent(studentLastName);

        if (results.isEmpty()) {
            System.out.println("Khong tim thay sinh vien " + studentLastName + ".");
        } else {
            Collections.sort(results, Comparators.NAMEANDID);
            System.out.println("Tim thay " + results.size() + " sinh vien co ten " + studentLastName + ":");
            for (int i = 0; i < results.size(); i++) {
                results.get(i).displayInformation();
            }
        }
    }

    private void runUpdateDelete() {
        String studentId;
        do {
            System.out.println("Nhap ID sinh vien can tim: ");
            studentId = sc.next();
            if (!isIdValid(studentId)) {
                System.out.println("ID khong hop le. Yeu cau nhap lai.");
            } else if (!ms.isStudentExisted(studentId)) {
                System.out.println("Sinh vien khong ton tai. Yeu cau nhap lai.");
            }
        } while (!ms.isStudentExisted(studentId));

        System.out.println("Thong tin hoc sinh so ID " + studentId + " : ");
        ms.searchStudent(studentId).displayInformation();

        System.out.println("Ban muon cap nhat (U) hay xoa (D) sinh vien? ");
        char selection = sc.next().charAt(0);
        if (selection == 'U' || selection == 'u') {
            System.out.println("Bam 1 de cap nhat ID cua sinh vien");
            System.out.println("Bam 2 de cap nhat ten cua sinh vien");
            System.out.println("Bam 3 de cap nhat hoc ky (1 hoac 2) cua sinh vien");
            System.out.println("Bam 4 de cap nhat ten khoa hoc (Java, .Net, C / C++) cua sinh vien");
            int updateSelection = sc.nextInt();
            String newId;
            String newFamilyName;
            String newLastName;
            String newName;
            String newYear;
            String newCourse;

            if (updateSelection == 1) {
                do {
                    System.out.println("Cap nhat ID: ");
                    newId = sc.next();
                    if (!isIdValid(newId)) {
                        System.out.println("ID khong hop le. Yeu cau nhap lai");
                    } else if (ms.isStudentExisted(newId)) {
                        System.out.println("ID da ton tai. Yeu cau nhap lai");
                    }
                } while (ms.isStudentExisted(newId) || !isIdValid(newId));

                ms.searchStudent(studentId).setId(newId);
                System.out.println("Cap nhat ID thanh cong.");
            } else if (updateSelection == 2) {
                do {
                    System.out.println("Cap nhat ten: ");
                    System.out.println("Nhap ho cua sinh vien: ");
                    newFamilyName = sc.next();
                    System.out.println("Nhap ten cua sinh vien: ");
                    newLastName = sc.next();

                    newName = newFamilyName + " " + newLastName;
                    if (isNameValid(newName)) {
                        System.out.println("Cap nhat ten thanh cong!");
                        ms.searchStudent(studentId).setFamilyName(newFamilyName);
                        ms.searchStudent(studentId).setLastName(newLastName);
                        ms.searchStudent(studentId).setName(newName);
                    } else {
                        System.out.println("Trung ten. Yeu cau nhap lai");
                    }
                } while (!isNameValid(newName));
            } else if (updateSelection == 3) {
                int newYearAfterValidation = -1;
                do {
                    System.out.println("Cap nhat hoc ky (1 hoac 2): ");
                    try {
                        newYear = sc.next();
                        isYearValid(newYear);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Chi nhap hoc ky 1 hoac 2. Yeu cau nhap lai.");
                        continue;
                    }
                    newYearAfterValidation = Integer.parseInt(newYear);
                    if (newYearAfterValidation != 1 && newYearAfterValidation != 2) {
                        System.out.println("So hoc ky phai la 1 hoac 2. Yeu cau nhap lai.");
                    } else {
                        System.out.println("Cap nhat hoc ky thanh cong!");
                    }
                } while (newYearAfterValidation != 1 && newYearAfterValidation != 2);
            } else if (updateSelection == 4) {
                do {
                    System.out.println("Cap nhat ten khoa hoc (Java, .Net, C / C++): ");
                    newCourse = sc.next();
                    newCourse = convertCourse(newCourse);
                    if (!newCourse.equals("Java") &&
                            !newCourse.equals(".Net") &&
                            !newCourse.equals("C") &&
                            !newCourse.equals("C++")) {
                        System.out.println("Nhap sai khoa hoc. Yeu cau nhap lai");
                    } else {
                        ms.searchStudent(studentId).setCourse(newCourse);
                        System.out.println("Cap nhat khoa hoc thanh cong!");
                    }
                } while (!newCourse.equals("Java") &&
                        !newCourse.equals(".Net") &&
                        !newCourse.equals("C") &&
                        !newCourse.equals("C++"));
            }
        } else if (selection == 'D' || selection == 'd') {
            ms.deleteStudent(studentId);
            System.out.println("Xoa sinh vien thanh cong!");
        }
    }

    private void runReport() {
        System.out.println("Bao cao sinh vien:");
        System.out.println("So ID | Ten | Khoa hoc | Hoc ky | ");
        for (int i = 0; i < ms.getStudents().size(); i++) {
            ms.getStudents().get(i).displayInformation();
        }
    }
}

