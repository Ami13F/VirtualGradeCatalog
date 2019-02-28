package common.Repository;

import common.Domain.HasID;

import java.util.List;

public interface PageCrudRepository<ID,E extends HasID<ID> > extends CrudRepository<ID,E>{

    List<E> getNext();
    List<E> getPrev();

    void setPage(int page);

    Integer getPage();

    boolean hasNext();
    boolean hasPrev();
}
