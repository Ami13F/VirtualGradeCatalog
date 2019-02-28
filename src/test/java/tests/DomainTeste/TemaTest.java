package tests.DomainTeste;

import common.Domain.Tema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TemaTest {

    Tema t = new Tema(1,"Map",5,3);
    @Test
    public void getId(){
        assertTrue(t.getID() == 1);
    }

    @Test
    public void setId(){
        t.setID(2);
        assertTrue(t.getID() == 2);
    }
    @Test
    public void getDescriere(){
        assertTrue(t.getDescriere().equals("Map"));
    }
    @Test
    public void setDescriere(){
        t.setDescriere("OOP");
        assertTrue(t.getDescriere().equals("OOP"));
    }
    @Test
    public void getDeadline(){
        assertTrue(t.getDeadline() == 5);
    }
    @Test
    public void setDeadline(){
        t.setDeadline(6);
        assertTrue(t.getDeadline() == 6);
    }
    @Test
    public void getDataPrimire(){
        assertTrue(t.getDataPrimire().equals(3));
    }
}
