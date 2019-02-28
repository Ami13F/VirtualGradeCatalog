package ValidatorPackage;

import Exceptions.ValidationException;
import common.Domain.Student;

public class StudentValidator implements Validator<Student> {

    @Override
    public void validate(Student entity) throws ValidationException {
        String msg = "";
        if( entity.getNumele().isEmpty() ) msg+="Nume gresit!";
        msg += validateEmail(entity.getEmail());
        if( entity.getGrupa()< 221 || entity.getGrupa()> 227 ) msg+="Grupa incorecta!";
        msg += validateProfesor(entity.getNumeProfesor());

        if(!msg.isEmpty())
            throw new ValidationException(msg);

    }

    public static String validateEmail(String email){
        if( email.equals("") || !(email.matches("[a-z0-9_.]+@[a-z]+[0-9]*\\.[a-z]{2,}")))
            return "Email gresit!";
        return "";
    }

    public static String validateProfesor(String prof){
        if( prof.isEmpty() || (!(prof.equals("Adriana")) && !(prof.equals("Camelia"))) )
            return "Profesor invalid!";
        return "";
    }
}
