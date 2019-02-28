package tests.RepositoryTeste;

import Exceptions.ValidationException;
import ValidatorPackage.TemeValidator;
import common.Domain.Tema;
import common.Repository.CrudRepository;
import common.Repository.TemeRepository;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemeRepositoryTest {

    private CrudRepository<Integer, Tema> repo = new TemeRepository(new TemeValidator(),"src/test/resources/TemeRepo.txt");
    @Test
    void extractEntity() {
        try {
            assertNull(repo.findOne(1));
        }catch(ValidationException v){
            assertTrue(true);
        }
    }
}