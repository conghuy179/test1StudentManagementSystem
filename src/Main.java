import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ManagementSystem ms = new ManagementSystem();
    public static void main(String[] args) {
        Driver driver = new Driver(ms, sc);
        driver.run();
    }
}