package ValidatorPackage;

import Exceptions.ValidationException;
import common.Domain.Nota;

public class NotaValidator implements Validator<Nota> {
    @Override
    public void validate(Nota entity) throws ValidationException {
        String msg= "";

        if(entity.getNota()< 0 || entity.getNota()>10) msg+="Nota gresita";
        if(entity.getStudent() == null) msg+= "Student invalid!";
        if(entity.getTema() == null) msg+="Tema invalida!";
        if(entity.getDataPredareTema() <1 || entity.getDataPredareTema()>14) msg+="Data predare gresita";
        if(msg.length()>0){
            throw new ValidationException(msg);
        }
    }
}
