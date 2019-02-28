package common.Repository;

import Exceptions.ValidationException;
import ValidatorPackage.StudentValidator;
import common.Domain.Student;

import java.util.Objects;


public class StudentRepository extends AbstractFileRepository<Integer,Student> {

    StudentValidator validator;
    String fileName;

    /**
     * Constructor
     *
     * @param val      Validator pentru Student
     * @param fileName Numele fisierului cu datele Studentului
     */
    public StudentRepository(StudentValidator val, String fileName) {
        super(fileName, val);
        this.validator = val;
        this.fileName = fileName;
        super.loadFromFile();
    }

    /**
     * @param line cu datele despre Student
     * @return entitate de tip Student
     */
    public Student extractEntity(String line) {
        if (Objects.equals(line, "")) throw new ValidationException("Empty file.");
        String[] s = line.split(" ");
        Student stu = new Student(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), s[3], s[4]);
        return stu;
    }



}