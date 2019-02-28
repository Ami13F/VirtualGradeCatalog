package tests.PasswordTest;

import common.Repository.RepositoryParola;
import common.Service.ServiceParola;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;

public class PasswordTest {

    ServiceParola serviceParola = new ServiceParola(new RepositoryParola("src/test/resources/Student.txt"),new RepositoryParola("src/test/resources/Profesor.txt"),new RepositoryParola("src/test/resources/Secretar.txt"));
    @Test
    public void convertPassword(){
        assertTrue(serviceParola.convertPassword("dana").equals("2C9E0A2585DC7406589A3724F0027811506E0F133726303A15D6779D532A2573"));
    }
}
