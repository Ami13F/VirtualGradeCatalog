package Exceptions;

public class RepoExceptions extends RuntimeException {


    public RepoExceptions(String message) {
        super(message);
    }
    public String what(){
        return super.getMessage();
    }

}
