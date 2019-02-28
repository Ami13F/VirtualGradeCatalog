package tests.RepositoryTeste;

import common.Domain.Student;
import common.Repository.AbstractFileRepository;
import common.Repository.StudentRepository;
import ValidatorPackage.StudentValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentRepositoryTest {

    AbstractFileRepository<Integer,Student> repo = new StudentRepository(new StudentValidator(),"src/test/resources/StudentRepo.txt");

    @Test
    void findOne() {
        //studentul exista
        Student s = repo.findOne(12234);
//        assertTrue(s.getNumele().equals("Oana"));

        //id-ul e null
        try{
            repo.findOne(null);
            assertTrue(false);
        }catch(IllegalArgumentException ex){
            assertTrue(true);
        }

        //nu exista studentul
        assertTrue(repo.findOne(123) == null);

    }



    @Test
    void save() {
        //Student s = repo.save(new Student(12235,"Maria",332,"maria@gmail.com","Ioana"));
        //assertTrue(s.getNumele().equals("Maria"));
        //s = repo.save(new Student(12222,"Cana",123,"cana@gmail.com","Boian"));
        //assertTrue(s == null);

    }

    @Test
    void delete() {
        Student s = new Student(12222,"Cana",123,"cana@gmail.com","Boian");
//        repo.delete(s.getID());
    }


    @Test
    void update() {
        //Student stu = new Student("Oana",212,"oana@yahoo.com","Miancu");
        //Student s1 = repo.update(stu);
        //assertTrue(s1==null);
        //Student s2 = new Student("Ioi",232,"ioi@yahoo.com","Vulpe");
        //s2 = repo.update(s2);
        //assertTrue(s2.getNumele().equals("Ioi"));

    }
}