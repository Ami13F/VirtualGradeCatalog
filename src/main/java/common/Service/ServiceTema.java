package common.Service;

import Exceptions.ValidationException;
import Utils.Calcule;
import common.Domain.Tema;
import common.Repository.CrudRepository;

public class ServiceTema {

    private CrudRepository<Integer, Tema> repoTeme;

    public ServiceTema(CrudRepository<Integer, Tema> repoTeme){
        this.repoTeme = repoTeme;
    }

    /**
     *
     * @param t Tema
     * @return null daca a fost salvat
     * @throws ValidationException  daca entity e invalid
     * @throws IllegalArgumentException daca entity e null
     */
    public Tema saveTema(Tema t){
        if(repoTeme.save(t)!=null){
            throw new ValidationException("Tema exista");
        }
        t = repoTeme.save(t);
        return t;
    }

    /**
     * Sterge Tema
     * @param id Integer
     * @return Tema daca a fost stearsa
     * @throws ValidationException Daca nu am putut sterge Tema
     */
    public Tema deleteTema(Integer id){
        if(repoTeme.delete(id)==null){
            throw new ValidationException("Tema nu a putut fi stearsa!");
        }
        return repoTeme.delete(id);
    }

    /**
     * Schimbam atributele pt o Tema existenta
     * @param t Tema
     * @return Tema
     * @throws ValidationException Daca nu putem face update
     */
    public Tema updateTema(Tema t){
        if(repoTeme.update(t) !=null){
            throw new ValidationException("Nu am putut face update!");
        }
        return repoTeme.update(t);

    }

    /**
     * Cauta o Tema dupa id
     * @param id Integer
     * @return Tema if exist
     * @throws ValidationException daca nu exista
     */
    public Tema findTema(Integer id){
        if(repoTeme.findOne(id) == null) throw new ValidationException("Tema nu poate fi gasita!");
        return repoTeme.findOne(id);

    }

    /**
     *
     * @param desc String
     * @return Tema daca exista, null altfel
     */
    public Tema findTemaDupaDescriere(String desc){
        for(Tema t : repoTeme.findAll()){
            if(t.getDescriere().equals(desc)){
                return t;
            }
        }return null;

    }

    /**
     * Schimba saptamana Curenta
     * @param newWeek noua SaptamanaCurenta
     */
    public void modificaSaptamana(Integer newWeek){
        Calcule.setSaptamana(newWeek);
    }

    /**
     * Cauta o tema dupa id.
     * Daca saptamana curenta e mai mica decat deadLine-ul modificam deadline=ul
     * @param id id-ul dupa care cauta tema
     * @param newDeadline noul deadLine
     * @return null daca nu am putut modifica
     * altfel returneaza Tema daca a fost modificat
     * @throws IllegalArgumentException if id = null
     * @throws ValidationException if newDeadline is not valid
     */
    public Tema prelungire(Integer id, Integer newDeadline) {
        Tema t = findTema(id);
        if(t == null){throw new ValidationException("Nu am putut schimba deadline-ul!");}
        if(t.getDeadline() > Calcule.getSaptamana()) {
            t.setDeadline(newDeadline);
            repoTeme.update(t);
            return t;
        }

        throw new ValidationException("Nu am putut schimba deadline-ul!");
    }

    /**
     *
     * @return Iterable toate Temele
     */
    public Iterable<Tema>findAllTema (){
        return repoTeme.findAll();
    }




}
