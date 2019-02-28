package common.Repository;

import Exceptions.ValidationException;
import ValidatorPackage.Validator;
import common.Domain.HasID;

import java.io.*;
import java.util.Objects;

public abstract class AbstractFileRepository<ID, E extends HasID<ID>> extends InMemoryRepository<ID,E> {

    protected String fileName;

    public AbstractFileRepository(String fileName,Validator<E> val) {
        super(val);
        this.fileName = fileName;
        loadFromFile();
    }

    /**
     * Load all data form file
     */
    public void loadFromFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line;

            while( (line = br.readLine())!=null){
                if(Objects.equals(line,"")) throw new ValidationException("Empty file.");
                E temp = extractEntity(line);
                super.save(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(ValidationException v){
            v.what();
        }
    }

    /**
     * Functia va fi implementata pentru fiecare element de tip E
     * @param entity String
     * @return E  - tip generic
     */
    public abstract E extractEntity(String entity);

    @Override
    public E save(E entity){
        E returnedEntity = super.save(entity);
        if(returnedEntity == null) {
            reload();
        }
        return returnedEntity;
    }


    public void reload(){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(fileName))){

            for(E s: super.findAll()){
                br.write(s.toString());
                br.write('\n');
            }
        }catch (FileNotFoundException ex){
            throw new ValidationException(ex.getMessage());
        }catch (IOException e){
            throw new ValidationException(e.getMessage());
        }

    }



    @Override
    public E delete(ID id) {
       E rez = super.delete(id);
       reload();
       return rez;
    }

    @Override
    public E update(E entity) {
        E rez = super.update(entity);
        reload();
        return rez;
    }


}
