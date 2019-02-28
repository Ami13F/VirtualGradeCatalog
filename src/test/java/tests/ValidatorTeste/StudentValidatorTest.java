package tests.ValidatorTeste;

import common.Domain.Student;
import Exceptions.ValidationException;
import ValidatorPackage.StudentValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentValidatorTest {

    StudentValidator val = new StudentValidator();
    @Test
    public void validate() {

        Student s1 = new Student(-1,"Ion",124,"ion@u","Dan");
        try{val.validate(s1);   //test id invalid
            assertTrue(false);
        }catch(ValidationException ms){
            assertTrue(true);
        }
        Student s2 = new Student(1,"",124,"ion@u","Dan");
        try{val.validate(s2);   //test nume invalid
            assertTrue(false);
        }catch(ValidationException ms){
            assertTrue(true);
        }

        Student s3 = new Student(1,"Ion",-14,"ion@u","Dan");
        try{val.validate(s3);   //test grupa invalida
            assertTrue(false);
        }catch(ValidationException ms){
            assertTrue(true);
        }
        Student s4 = new Student(1,"Ion",124,"","Dan");
        try{val.validate(s4);   //test email invalid
            assertTrue(false);
        }catch(ValidationException ms){
            assertTrue(true);
        }
        Student s5 = new Student(1,"Ion",124,"ion@u","");
        try{val.validate(s5);   //test prof invalid
            assertTrue(false);
        }catch(ValidationException ms){
            assertTrue(true);
        }
    }
}