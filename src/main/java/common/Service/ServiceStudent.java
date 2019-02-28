package common.Service;

import Exceptions.ValidationException;
import Utils.Observer.Observable;
import Utils.Observer.Observer;
import common.Domain.Student;
import common.Repository.PageCrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceStudent implements Observable {
    private PageCrudRepository<Integer,Student> repoStud;

    private List<Observer> observers=new ArrayList<>();

    /**
     * @param repoStud common.Repository pt Student
     */
    public ServiceStudent(PageCrudRepository<Integer,Student> repoStud) {
        this.repoStud = repoStud;
    }

    /**
     *
     * @param s Student
     * entity trebuie sa nu fie null
     * @return null daca l-am adaugat
     *          returneaza Studentul altfel
     * @throws ValidationException daca Studentul nu e valid
     * @throws IllegalArgumentException daca Studentul e null
     */
    public Student saveStudent(Student s) throws ValidationException {

        //caut studentul dupa nume si grupa
        if(s.getNumele() == null || s.getGrupa() == null ) return null;
        for(Student st : repoStud.findAll()){
            if(st.getGrupa().equals(s.getGrupa()) && st.getNumele().equals(s.getNumele())){
                throw new ValidationException("Studentul exista!");
            }
        }


        for(int i=1;i<=getAll().size()+1;i++){
            if(repoStud.findOne(i)==null)
                s.setID(i);
        }

        if(repoStud.save(s)!=null ){
            throw new ValidationException("Studentul exista!");
        }
        s = repoStud.save(s);
        notifyObservers();

        return s;

    }

    /**
     * Cauta un student dupa id si-l sterge daca exita
     * @param idStudent Integer
     * @return  s de tip Student
     * @throws ValidationException daca studentul nu exista
     */
    public Student deleteStudent(Integer idStudent) throws ValidationException {
        if(repoStud.findOne(idStudent)!= null){
            Student s = repoStud.delete(idStudent);
            notifyObservers();
            return s;
        }
        throw new ValidationException("Studentul nu exista!");
    }

    /**
     * Update pt Student
     * @param s Student
     * @return null if Update complete
     * @throws ValidationException Update fail
     */
    public Student updateStudent(Student s)throws ValidationException {
        Student sN = new Student();
        if(repoStud.update(s)!=null){
            throw new ValidationException("Nu am putut face update!");
        }
        notifyObservers();

        return null;

    }

    /**
     *
     * @return all Students
     */
    public Iterable<Student> findAllStudent()
    {
        return repoStud.findAll();
    }

    public Student findStudentDupaNumeSiGrupa(String nume, Integer grupa){

        if(nume == null || grupa == null ) return null;
        for(Student s : repoStud.findAll()){
            if(s.getGrupa().equals(grupa) && s.getNumele().equals(nume)){
                return s;
            }
        }
        throw new ValidationException("Studentul nu exista!");
    }
    /**
     * Find one Student
     * @param id Integer
     * @return Students if exist
     * @throws IllegalArgumentException if id is null
     */
    public Student findStudent(Integer id){

        if(id == null){throw new IllegalArgumentException("Id invalid");}
        if(repoStud.findOne(id) ==null){
            throw new ValidationException("Studentul nu exista");
        }
        return repoStud.findOne(id);
    }

    public List<Student> getAll(){
        return StreamSupport.stream(repoStud.findAll().spliterator(),false).collect(Collectors.toList());
    }

    /**
     *
     * @return Integer nr maxim de pagini
     */
    public Integer maxNumberOfPage(){
        return getAll().size()/20+1;
    }

    public void setPage(int page){
        repoStud.setPage(page);
    }

    public Integer getPage(){
        return repoStud.getPage();
    }

    public List<Student> getNext(){
        return repoStud.getNext();
    }

    public List<Student> getPrev(){
        return repoStud.getPrev();
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
