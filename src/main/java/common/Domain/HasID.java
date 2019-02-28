package common.Domain;

public interface HasID<ID> {
    /**
     *
     * @return ID
     */
    ID getID();

    /**
     *
     * @param id ID
     */
    void setID(ID id);
}
