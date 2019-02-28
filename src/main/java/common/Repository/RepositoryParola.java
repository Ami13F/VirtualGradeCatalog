package common.Repository;

import Exceptions.ValidationException;
import common.Domain.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryParola {

    private String fileName;
    private List<User>  utilizatori = new ArrayList<>();

    public RepositoryParola(String fileName){
        this.fileName = fileName;
        loadFromFile();
    }

    private void loadFromFile(){
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            while((line = br.readLine())!=null) {
                if(Objects.equals(line,"")) throw new ValidationException("Empty file.");
                String[] l =  line.split(" ");
                User us = new User(l[0],l[1]);
                utilizatori.add(us);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(ValidationException v){
            v.what();
        }

    }

    public List<User> getUsers(){
        return utilizatori;
    }


}
