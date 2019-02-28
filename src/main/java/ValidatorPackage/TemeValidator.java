package ValidatorPackage;

import Exceptions.ValidationException;
import common.Domain.Tema;

public class TemeValidator implements Validator<Tema> {
    @Override
    public void validate(Tema entity) throws ValidationException {
        String msg = new String();
        if( entity.getID() < 0) msg+="Id tema gresit!";
        if(entity.getDeadline()<1 || entity.getDeadline()>14) msg+="Wrong deadline!";
        if(entity.getDataPrimire() < 1 || entity.getDataPrimire() >14) msg+= "Data predare gresita!";
        if(entity.getDescriere().isEmpty()) msg+="Descriere gresita!";
        if(!msg.isEmpty()){
            throw new ValidationException(msg);
        }
    }
}
