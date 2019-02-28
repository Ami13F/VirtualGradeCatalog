package tests.DomainTeste;

import Utils.Calcule;
import common.Domain.Nota;
import common.Domain.Student;
import common.Domain.Tema;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class NotaTest {


    @Test
    public void calculeazaNota() {
        Calcule.setSaptamana(5);
        //fara intarzieri
        Student s =new Student(1,"Ion",232,"ion","prof");
        Tema t1 = new Tema(3,"Lab1",6,4);
        Nota n1 = new Nota(t1,s,10f,5);
        assertEquals(10f, n1.getNota());

        //fara intarzieri
        Tema t2 = new Tema(3,"Lab1",5,4);
        Nota n2 = new Nota(t2,s,10f,5);
        assertEquals(10f, n2.getNota());

        //intarziere o saptamana
        Tema t3 = new Tema(3,"Lab1",4,3);
        Nota n3 = new Nota(t3,s,10f,5);
        assertEquals(7.5f, n3.getNota());

        //intarziere 2 saptamani
        Tema t4 = new Tema(3,"Lab1",3,2);
        Nota n4 = new Nota(t4,s,10f,5);
        assertEquals(5f, n4.getNota());

        //intarziere mai mult de 2 saptamani
        Tema t5 = new Tema(3,"Lab1",2,1);
        Nota n5 = new Nota(t5,s,10f,5);
        assertEquals(1f, n5.getNota());



    }
}