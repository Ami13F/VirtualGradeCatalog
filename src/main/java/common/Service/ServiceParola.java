package common.Service;

import Exceptions.ValidationException;
import Utils.Converter;
import common.Domain.User;
import common.Repository.RepositoryParola;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServiceParola {

    private RepositoryParola repoProf;
    private RepositoryParola repoSecretar;
    private RepositoryParola repoStudent;

    public ServiceParola(RepositoryParola repoProf, RepositoryParola repoSecretar,RepositoryParola repoStudent){
        this.repoProf = repoProf;
        this.repoSecretar = repoSecretar;
        this.repoStudent = repoStudent;
    }

    /**
     *
     * @param user Un user nou
     * @return String -> Secretar sau Prof
     * @throws ValidationException daca nu exista userul
     */
    public String findUser(User user){
        user.setPassword(convertPassword(user.getPassword()));
        for(User us : repoProf.getUsers()){
            if(us.getPassword().equals(user.getPassword()) && us.getUserName().equals(user.getUserName()))
                return "Profesor";
        }
        for(User us : repoSecretar.getUsers()) {
            if (us.getPassword().equals(user.getPassword()) && us.getUserName().equals(user.getUserName()))
                return "Secretar";
        }
        for(User us : repoStudent.getUsers()) {
            if (us.getPassword().equals(user.getPassword()) && us.getUserName().equals(user.getUserName()))
                return "Student";
        }
        throw new ValidationException("Date invalide,user-ul nu exista");
    }

    /**
     * O functie care cripteaza parola.
     * @param password String - parola introdusa de user
     * @return Parola criptata
     */
    public String convertPassword(String password) {
        byte[] bytePass = password.getBytes();
        try {
            MessageDigest messageDigest;
            messageDigest = MessageDigest.getInstance("SHA-256"); //tipul de convertor
            byte[] hashPassword = messageDigest.digest(bytePass);
            return Converter.bytesToHex(hashPassword);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
