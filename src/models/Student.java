package models;

public class Student {
    private String id;
    private String name;
    private int year;
    private String course;

    public Student(String id, String name, int year, String course) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.course = course;
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

    public void setFamilyAndLastName(String familyName, String lastName) {
        this.name = familyName + ' ' + lastName;
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
