package models;

public class Student {
    private String id;
    private String name;
    private String familyName;
    private String lastName;
    private int year;
    private String course;

    public Student(String id, String name, int year, String course) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.course = course;
    }

    public Student(String id, String familyName, String lastName, int year, String course) {
        this.id = id;
        this.familyName = familyName;
        this.lastName = lastName;
        this.name = familyName + ' ' + lastName;
        this.year = year;
        this.course = course;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void displayInformation() {
        System.out.printf(" %s | %s | %s | %s\n",
                getId(),
                getName(),
                getYear(),
                getCourse());
    }

}
