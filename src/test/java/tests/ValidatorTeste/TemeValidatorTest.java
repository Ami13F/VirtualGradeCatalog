package tests.ValidatorTeste;

import common.Domain.Tema;
import Exceptions.ValidationException;
import ValidatorPackage.TemeValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TemeValidatorTest {

    TemeValidator val = new TemeValidator();
    @Test
    void validate() {
        Tema t = new Tema(-12,"Map",12,10);
        try{
            val.validate(t);
            assertTrue(false);
        }catch(ValidationException e){
            assertTrue(true);
        }
        Tema t2 = new Tema(12,"",12,10);
        try{
            val.validate(t2);
            assertTrue(false);
        }catch(ValidationException e){
            assertTrue(true);
        }
        Tema t3 = new Tema(12,"Map",-12,10);
        try{
            val.validate(t3);
            assertTrue(false);
        }catch(ValidationException e){
            assertTrue(true);
        }
        Tema t4 = new Tema(12,"Map",12,104);
        try{
            val.validate(t4);
            assertTrue(false);
        }catch(ValidationException e){
            assertTrue(true);
        }
    }
}