package lab1.task2;

public class Student {
    private String name;
    private double grade;

    public String getName()
    {
        return name;
    }

    public double getGrade()
    {
        return grade;
    }

    public Student(String name_, double grade_)
    {
        name = name_;
        grade = grade_;
    }
    @Override
    public String toString() {
        return "Nume: " + name.toString() + ", Nota: " + grade + '\n';
    }
}
