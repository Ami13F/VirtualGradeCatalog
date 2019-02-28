package tests.DomainTeste;

import common.Domain.Student;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class StudentTest {

    Student s1 = new Student(12234,"Maria",232,"maria@yahoo.com","Cristi");

    @Test
    public void getNumele() {
        assertTrue(s1.getNumele().equals("Maria"));
    }

    @Test
    public void setNumele() {
        s1.setNumele("Cristina");
        assertTrue(s1.getNumele() == "Cristina");
    }

    @Test
    public void getGrupa() {
        assertTrue(s1.getGrupa() == 232);
    }

    @Test
    public void setGrupa() {
        s1.setGrupa(223);
        assertTrue(s1.getGrupa()==223);
    }

    @Test
    public void getEmail() {
        assertTrue(s1.getEmail() =="maria@yahoo.com");
    }

    @Test
    public void setEmail() {
        s1.setEmail("cris@gmail.com");
        assertTrue(s1.getEmail()=="cris@gmail.com");
    }

    @Test
    public void getNumeProfesor() {
        assertTrue(s1.getNumeProfesor()=="Cristi");
    }

    @Test
    public void setNumeProfesor() {
        s1.setNumeProfesor("Dan");
        assertTrue(s1.getNumeProfesor()=="Dan");
    }

    @Test
    public void getID() {
        assertTrue(s1.getID() == 12234);
    }

    @Test
    public void setID() {
        s1.setID(2223);
        assertTrue(s1.getID() == 2223);
    }
}