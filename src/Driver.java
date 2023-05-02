import java.util.Scanner;

public class Driver {
    public static final int CHOICE_CREATE = 1;
    public static final int CHOICE_SEARCH_SORT = 2;
    public static final int CHOICE_UPDATE_DELETE = 3;
    public static final int CHOICE_REPORT = 4;
    public static final int CHOICE_EXIT = 5;
    private int selection;
    private Scanner sc;
    private ManagementSystem ms;
    private int studentNumber = 0;

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

//    public static boolean testDigit(int selection) {
//        boolean isDigit = false;
//        if (selection == (int) selection) {
//            isDigit = true;
//        }
//
//        if (!isDigit) {
//            throw new IllegalArgumentException("Yeu cau nhap so");
//        }
//        boolean answer = false;
//
//        if (selection < 5 && selection > 0) {
//            answer = true;
//        } else {
//            System.out.println("Chi nhap lua chon trong 1 den 5");
//        }
//        return answer;
//    }

    public void run() {
        do {
            printMenu();
            selection = sc.nextInt();
//            try {
//                testDigit(selection);
//                testDigit('a');
//            } catch (IllegalArgumentException e) {
//                System.out.println("IllegalArgumentException => " + e.getMessage());
//            }
            if (selection == CHOICE_CREATE) {
                runCreate();
            }
        } while (selection != CHOICE_EXIT);
    }

    public void runCreate() {
        System.out.println("Ban da chon: Tao sinh vien");
        System.out.println("Nhap thong tin sinh vien");
        char continueOrNot;
        do {
            createNewStudent();
            if (studentNumber > 9) {
                System.out.println("Ban co muon tao hoc sinh tiep (Y/N) khong? ");
                System.out.println("Chon Y de tiep tuc, N de quay lai man hinh chinh");
                continueOrNot = sc.next().charAt(0);
                if (continueOrNot == 'Y') {
                    do {
                        createNewStudent();
                        studentNumber++;
                        System.out.println("Ban co muon tao hoc sinh tiep (Y/N) khong? ");
                        System.out.println("Chon Y de tiep tuc, N de quay lai man hinh chinh");
                        continueOrNot = sc.next().charAt(0);
                    } while (continueOrNot == 'N');
                } else if (continueOrNot == 'N') {
                    break;
                }
            }
        } while (studentNumber < 10);
    }

    public void createNewStudent() {
        String studentId;
        String studentName;
        int studentYear;
        String studentCourse;

        System.out.println("Nhap ID sinh vien: ");
        studentId = sc.next();
        System.out.println("Nhap ten sinh vien: ");
        studentName = sc.next();
        System.out.println("Nhap so hoc ky (1 hoac 2): ");
        studentYear = sc.nextInt();
        System.out.println("Nhap ten khoa hoc (Java, .Net, C / C++): ");
        studentCourse = sc.next();
        ms.addStudent(new Student(studentId, studentName, studentYear, studentCourse));
        studentNumber++;
    }
}

