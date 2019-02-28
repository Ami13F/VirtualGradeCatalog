package common.Domain;

public class Tema implements HasID<Integer>{

    private Integer idTema;
    private String descriere;
    private Integer deadline;
    private Integer dataPrimire;   //data la care s-a dat tema la laborator

    /**
     *
     * @param idTema Integer
     * @param descriere String
     * @param deadline Integer
     * @param dataPrimire Integer
     */
    public Tema(Integer idTema, String descriere, Integer deadline, Integer dataPrimire) {
        this.idTema = idTema;
        this.descriere = descriere;
        this.deadline = deadline;
        this.dataPrimire = dataPrimire;

    }

    public Tema(){

    }
    /**
     *
     * @return idTema - Integer
     */
    public Integer getId(){
        return idTema;
    }

    /**
     *
     * @return descriere - String
     */
    public String getDescriere(){
        return this.descriere;
    }

    /**
     *
     * @return deadline - Integer
     */
    public Integer getDeadline(){
        return this.deadline;
    }

    /**
     *
     * @return dataPrimire - Integer
     */
    public Integer getDataPrimire(){
        return this.dataPrimire;
    }


    /**
     *
     * @param descriere - String
     */
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    /**
     *
     * @param deadline - Integer
     */
    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }



    /**
     *
     * @param dataPrimire - Integer
     */
    public void setDataPrimire(Integer dataPrimire) {
        this.dataPrimire = dataPrimire;
    }

    @Override
    public String toString() {
        return idTema +" "+
                descriere + " " +
                deadline +" "+
                dataPrimire;
    }

    /**
     *
     * @return - Integer
     */
    @Override
    public Integer getID() {
        return this.idTema;
    }

    /**
     *
     * @param in - Integer
     */
    @Override
    public void setID(Integer in) {
        this.idTema = in;
    }
}
