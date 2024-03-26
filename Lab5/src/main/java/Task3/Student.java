package Task3;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String fullName;

    // Acest dicționar conține informații cu privire la notele
    // pe care un student le are la un curs. Intrările sunt de tip
    // numeCurs -> notăCurs
    private Map<String, Double> courseInformation;
    public Student() {
        this.fullName = "Ion Ion";
        this.courseInformation = new HashMap<>(){};
    }
    public void AddToCourse(String courseName, Double grade) {
        this.courseInformation.putIfAbsent(courseName, grade);
    }

    @Override
    public Student clone() {
        Student student = new Student();
        student.fullName = this.fullName;
        student.courseInformation = new HashMap<>(this.courseInformation);
        return student;
    }

    @Override
    public String toString() {
        return fullName + " " + courseInformation;
    }
}

class Main
{
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.toString());

        Student studentClona = student.clone();
        studentClona.AddToCourse("Fizica", 8.0);
        System.out.println(studentClona.toString());
        System.out.println(student.toString());
    }
}