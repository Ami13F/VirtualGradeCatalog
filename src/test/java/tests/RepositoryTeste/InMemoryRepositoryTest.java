package tests.RepositoryTeste;

import ValidatorPackage.NotaValidator;
import common.Domain.Nota;
import common.Domain.Student;
import common.Domain.Tema;
import common.Repository.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

class InMemoryRepositoryTest {

    InMemoryRepository<String, Nota> repoNote = new InMemoryRepository<>(new NotaValidator());

    void init(){
        Tema t = new Tema(5,"Lab1",12,10);
        Student s = new Student(13,"Ion",321,"ion@you","Prof");
       // Nota n1 = new Nota(1,t,s,9f);
       // Nota n2 = new Nota(2,t,s,3f);
        //repoNote.save(n1);
        //repoNote.save(n2);
    }
    @Test
    void findOne() {

        try {
            repoNote.findOne(null);
            assertTrue(false);
        }catch(IllegalArgumentException e){
            assertTrue(true);
        }
        init();
      //  assertTrue(repoNote.findOne("") == null);
       // assertTrue(repoNote.findOne(2).getStudent().getID() == 13);
    }

    @Test
    void findAll() {
        init();
        repoNote.findAll();
    }

    @Test
    void save() {
        Student s = new Student(13,"Ion",123,"ion@i","prof");
        init();
        Tema t = new Tema(5,"Lab1",12,10);
       // Nota n2 = new Nota(2,t,s,3f);
        try {
            repoNote.save(null);
            assertTrue(false);
        }catch(IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void delete() {
        init();
       // repoNote.delete(2);
        try{
            repoNote.delete(null);
            assertTrue(false);
        }catch(IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    void update() {
        try{
        repoNote.update(null);
        fail();
        }catch(IllegalArgumentException e){
            assertTrue(true);
        }
        init();
        Student s = new Student(13,"Ion",123,"ion@i","prof");
        init();
        Tema t = new Tema(5,"Lab1",12,10);
        //Nota n2 = new Nota(2,t,s,3f);
        //repoNote.update(n2);
    }

}