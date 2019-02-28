package common.Repository;

import Exceptions.ValidationException;
import ValidatorPackage.Validator;
import common.Domain.HasID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<ID,E extends HasID<ID>> implements PageCrudRepository<ID,E> {

    private Validator<E> val;
    Map<ID,E> entities;

    Integer currentPage = 0;
    Integer paginiIncarcate = 20;



    public InMemoryRepository(Validator<E>val){
        this.val = val;
        entities = new HashMap<>();
    }

    /**
     *
     * @param id -the id of the entity to be returned
     *         id must not be null
     * @return entity
     *  * @throws IllegalArgumentException
     *    if id is null.
     */
    @Override
    public E findOne(ID id) {
        if (id == null){
            throw new IllegalArgumentException("Id must be not null");
        }
        return entities.get(id);
    }

    /**
     *
     * @return all entities
     */
    @Override
    public Iterable<E> findAll() {
        return entities.values();

    }

    /**
     *
     * @param entity
     * entity must be not null
     * @return null daca l-am adaugat
     * @return entity daca nu l-am putut adauga
     * @throws ValidationException
     * @throws IllegalArgumentException daca entity e null
     */
    @Override
    public E save(E entity) throws ValidationException {
        if(entity == null) throw new IllegalArgumentException("Entitate invalida");
        val.validate(entity);
        if(entities.get(entity.getID())== null) { //nu exista entity
            entities.put(entity.getID(),entity);
            return null;
        }
        return entity;
    }
    /**
     * removes the entity with the specified id
     * @param id
     * id must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws IllegalArgumentException
     * if the given id is null.
     */
    @Override
    public E delete(ID id) {
        if( id  == null) throw new IllegalArgumentException();
        if(entities.get(id) == null){
            return null;
        }
        return entities.remove(id);
    }

    /**
     *
     * @param entity
     * entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity - (e.g id does not exist).
     * @throws IllegalArgumentException
     * if the given entity is null.
     * @throws ValidationException
     * if the entity is not valid.
     */
    @Override
    public E update(E entity) {
        if( entity  == null) throw new IllegalArgumentException("entity null");
        val.validate(entity);
        if(entities.get(entity.getID()) == null){
            return entity;
        }
        entities.put(entity.getID(),entity);
        return null;
    }



    /**
     * @return null Daca nu mai are elemente
     */
    @Override
    public List<E> getNext() {
        List<E> lista = new ArrayList<>();
        Integer firstElem = currentPage * paginiIncarcate;
        Integer lastElem = (currentPage+1) * paginiIncarcate;
        if(!hasNext()){
            return null;
        }
        getElems(lista, firstElem, lastElem);
        currentPage +=1;
        return lista;
    }

    @Override
    public void setPage(int page){
        this.currentPage = Math.max(page, 0);

    }
    @Override
    public Integer getPage(){
        return currentPage;
    }

    @Override
    public List<E> getPrev() {
        if(!hasPrev()){
            return null;
        }
        currentPage = Math.max(0, currentPage - 1);

        List<E> lista = new ArrayList<>();

        Integer firstElem = currentPage * paginiIncarcate;
        Integer lastElem = (currentPage+1) * paginiIncarcate;

        getElems(lista, firstElem, lastElem);

        return lista;
    }

    private void getElems(List<E> lista, Integer firstElem, Integer lastElem) {
        Integer numElems = Math.min(entities.size(), lastElem);

        for(int i = firstElem;i< numElems;i++){
            lista.add((new ArrayList<E>(entities.values())).get(i));
        }
    }

    @Override
    public boolean hasNext() {
        return entities.values().size() > currentPage * paginiIncarcate;
    }

    @Override
    public boolean hasPrev() {
        return (currentPage > 0 );
    }
}
