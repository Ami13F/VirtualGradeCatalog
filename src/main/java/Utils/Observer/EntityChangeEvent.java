package Utils.Observer;


public class EntityChangeEvent <E> implements Event{
    private E data,oldData;
    private ChangeEventType type;

    public EntityChangeEvent(ChangeEventType type,E data){
        this.type = type;
        this.data = data;
    }
    public EntityChangeEvent(ChangeEventType type,E data,E oldData){
        this.type = type;
        this.data = data;
        this.oldData= oldData;
    }

    public ChangeEventType getType(){return type;}

    public E getData(){return data;}

    public E getOldData(){return oldData;}

}
