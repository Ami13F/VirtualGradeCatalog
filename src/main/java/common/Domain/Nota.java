package common.Domain;

public class Nota implements HasID<String> {

    private String id;
    private Tema tema;
    private Student student;
    private Float nota;
    private Boolean motivat;
    private Integer dataPredareTema;
    private Integer penalizare =0 ;

    /**
     * 
     * @param dataPredareTema Data la cara a fost predata tema de catre student
     * @param tema Tema pt o anumita nota
     * @param student   entitate de tip Student
     * @param nota Integer
     */
    public Nota(Tema tema, Student student, Float nota, Integer dataPredareTema) {
        this.id = tema.getID().toString()+student.getID().toString();
        this.student = student;
        this.tema = tema;
        this.motivat = false;
        this.dataPredareTema = dataPredareTema;
        this.nota = calculeazaNota(nota);
    }

    public Nota(){}

    public Float calcNota(Float nota,Boolean motivat){
        this.motivat = motivat;
        if(motivat)
            penalizare = 0;
        return calculeazaNota(nota);
    }

    /**
     *
     * @param nota nota la care va fi calculata
     * @return nota dupa modificare
     */
    private Float calculeazaNota(Float nota){
        if(this.motivat){return nota;}

        if(tema.getDeadline() >= this.dataPredareTema){
            return nota;
        }
        else if(this.dataPredareTema - tema.getDeadline()> 2){
            penalizare = 9;
            return (float)1;
        }
        int nr = dataPredareTema - tema.getDeadline();
        penalizare = nr;
        nota = nota - nr * 2.5f;
        return nota;
    }

    public Integer getPenalizare(){
        return penalizare;
    }

    /**
     * @return nota - Float
     */
    public Float getNota(){
        return this.nota;
    }

    /**
     * @param n - Float
     */
    public void setNota(Float n){
        this.nota = n;
    }

    /**
     * @return tema - Tema
     */
    public Tema getTema() {
        return tema;
    }

    /**
     * @param tema - Tema
     */
    public void setTema(Tema tema) {
        this.tema = tema;
    }

    /**
     *
     * @return student - Student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student - Student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return Boolean Motivat = true
     */
    public Boolean getMotivat(){
        return motivat;
    }


    public Integer getDataPredareTema(){
        return this.dataPredareTema ;
    }

    /**
     * @return id - Integer
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * @param id - Integer
     */
    @Override
    public void setID(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return tema.getID()+" "+
               tema.getDescriere()+" "+
               tema.getDeadline()+" "+
                tema.getDataPrimire()+" "+
                student.getID()+" "+
                student.getNumele()+ " "+
                student.getGrupa()+" "+
                student.getEmail()+" "+
                student.getNumeProfesor()+" "+
                nota +" "+
                dataPredareTema+" ";


    }


}

