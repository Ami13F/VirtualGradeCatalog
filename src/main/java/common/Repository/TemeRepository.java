package common.Repository;

import Exceptions.ValidationException;
import ValidatorPackage.TemeValidator;
import common.Domain.Tema;

import java.util.Objects;

public class TemeRepository extends AbstractFileRepository<Integer, Tema> {


    TemeValidator validator = new TemeValidator();
    private String fileName;

    public TemeRepository(TemeValidator val,String fileName) {
        super(fileName,val);
        this.validator = val;
        this.fileName =fileName;
        super.loadFromFile();
    }


    @Override
    public Tema extractEntity(String entity) {
        if(Objects.equals(entity,"")) throw new ValidationException("Empty file.");
        String[] s = entity.split(" ");
        Tema t = new Tema(Integer.parseInt(s[0]),s[1],Integer.parseInt(s[2]),Integer.parseInt(s[3]));
        return t;
    }
    /**
     * Vreau sa prelungesc tema daca deadline-ul e mai tarziu decat sapt curenta
     */
    /*public Tema prelungire(Tema t,Integer newDeadline){
        if( t == null){
            return null;
        }

      super.reload();
      return t;
    }*/

}