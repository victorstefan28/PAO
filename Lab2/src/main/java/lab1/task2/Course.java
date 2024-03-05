package lab1.task2;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Course {
    private String name;
    private double minimumGrade;

    private Student[] students;

    public Course(String name_, double minGrade_, Student[] students_)
    {
        name = name_;
        minimumGrade = minGrade_;
        students = students_;
    }

    public Student chooseStudentRandomly()
    {
        int rndIndex = new Random().nextInt(students.length);
        return students[rndIndex];
    }

    private Student[] showAllPassingStudents()
    {

        Stream<Student> passingStudents
                = Arrays.stream(students).filter(student -> student.getGrade() >= minimumGrade);

        return passingStudents.toArray(Student[]::new);
    }

    public void printAllPassingStudents()
    {
        System.out.println(Arrays.toString(showAllPassingStudents()));
    }

    public boolean IsStudentPassing(Integer index)
    {
        if(index < 0 || index >= students.length)
            return false;
        return students[index].getGrade() >= 5;
    }

    public boolean IsStudentPassing(String name_)
    {
        try{
            Optional<Student> studentByName = Arrays.stream(students).findAny().filter(student -> Objects.equals(student.getName(), name_));
            if(studentByName.isEmpty())
                return false;

            return studentByName.get().getGrade() >= minimumGrade;
        }catch(Throwable e)
        {
            return false;
        }


    }


}
