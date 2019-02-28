package common.Service;

import Exceptions.ValidationException;
import Utils.Observer.Observable;
import Utils.Observer.Observer;
import common.Domain.Nota;
import common.Repository.CrudRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceNota implements Observable {

    /**
     * CrudRepository for Notes
     * STRATEGY
     */
    private static CrudRepository<String, Nota> repoNote ;

    private List<Observer> observers=new ArrayList<>();

    public ServiceNota(CrudRepository<String, Nota> repoNote){
        ServiceNota.repoNote =repoNote;
    }

    /**
     * Creeaza un fisier cu numele studentului si notele acestuia.
     * @param n Nota care va fi adaugata unui student
     * @param feedback String
     * @throws IOException Daca fisierul nu a putut fi deschis
     */
    private void createStudentNotaFile(Nota n, String feedback) throws IOException {
        String numeStud = "src/main/java/DataAll/DateStudenti/"+ n.getStudent().getNumele()+".txt";
        File file = new File(numeStud);
        if(!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter br = new BufferedWriter(new FileWriter(file,true));
        br.write("Tema: " + n.getTema().getID().toString());
        br.newLine();
        br.write("Nota: "+ n.getNota().toString());
        br.newLine();
        br.write("Predata_in_saptamana: "+ n.getDataPredareTema().toString());
        br.newLine();
        br.write("Deadline: " + n.getTema().getDeadline().toString());
        br.newLine();
        br.write("Feedback: "+ feedback);
        br.newLine();
        br.newLine();
        try{
            br.close();
        }catch(Exception ex){
            throw new ValidationException("Error in closing the BufferedWriter"+ex);
        }

    }

    /**
     *
     * @param id String
     * @return null Daca nota nu exista
     *      Nota altfel
     */
    public Nota findNota(String id){
        for(Nota n : repoNote.findAll()){
            if(n.getID().equals(id))
                return n;
        }return null;
    }

    /**
     *
     * @param n Nota care trebuie salvata
     * @param feedback String
     * @throws ValidationException Daca nota exista
     * @throws IOException Daca nu am putut deschide fisierul
     */
    public void saveNota(Nota n, String feedback) throws ValidationException, IOException {
        if(repoNote.save(n)!=null){
            throw new ValidationException("Nota exista");
        }
        repoNote.save(n);

        createStudentNotaFile(n,feedback);
        notifyObservers();

    }

    /**
     * Sterge Nota
     * @param id Id-ul notei
     * @return Nota care a fost stearsa
     */
    public Nota deleteNota(String id){
        Nota n = repoNote.delete(id);
        if(n ==null){
            throw new ValidationException("Nota nu a putut fi stearsa!");
        }

        notifyObservers();
        return n;
    }


    /**
     *
     * @return Iterable<Nota>
     */
    public Iterable<Nota>findAllNota (){
        return repoNote.findAll();
    }

    /**
     * Lista cu toate notele.
     * @return List<Nota>
     */
    public static List<Nota>getAllNota(){
        List <Nota> l = new ArrayList<>();
        for(Nota n : repoNote.findAll()){
            l.add(n);
        }

        return l;
    }


    @Override
    public void addObserver(Observer e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }



}


