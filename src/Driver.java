import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    public static final int CHOICE_CREATE = 1;
    public static final int CHOICE_SEARCH_SORT = 2;
    public static final int CHOICE_UPDATE_DELETE = 3;
    public static final int CHOICE_REPORT = 4;
    public static final int CHOICE_EXIT = 5;

    public static final int MINIMUM_STUDENT_CREATED = 2;
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
            }
        } while (selection != CHOICE_EXIT);
    }

    public void runCreate() {
        System.out.println("Ban da chon: Tao sinh vien");
        System.out.println("Nhap thong tin sinh vien");
        char continueOrNot;
        do {
            createNewStudent();
        } while (ms.getNumStudents() < MINIMUM_STUDENT_CREATED);

        do {
            System.out.println("Ban co muon tao hoc sinh tiep (Y/N) khong? ");
            System.out.println("Chon Y de tiep tuc, N de quay lai man hinh chinh");
            continueOrNot = sc.next().charAt(0);
            if (continueOrNot == 'Y') {
                createNewStudent();
            } else if (continueOrNot != 'N') {
                System.out.println("Lua chon khong hop le");
            }
        } while (continueOrNot != 'N');
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
    }
}

