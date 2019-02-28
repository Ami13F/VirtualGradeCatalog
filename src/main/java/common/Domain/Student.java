package common.Domain;

public class Student implements HasID<Integer> {

    private Integer idStudent;
    private String numele;
    private Integer grupa;
    private String email;
    private String numeProfesor;

    /**
     * Constructor
     * @param idStudent id-ul Studentului de tip Integer
     * @param numele    String
     * @param grupa     Integer
     * @param email     -String
     * @param numeProfesor  -String
     */
    public Student(Integer idStudent, String numele, Integer grupa, String email, String numeProfesor) {
        this.idStudent = idStudent;
        this.numele = numele;
        this.grupa = grupa;
        this.email = email;
        this.numeProfesor = numeProfesor;
    }

    /**
     * Constructor student fara id
     * @param numele -String
     * @param grupa - Integer
     * @param email -String
     * @param numeProfesor -String
     */
    public Student(String numele, Integer grupa, String email, String numeProfesor) {
        this.numele = numele;
        this.grupa = grupa;
        this.email = email;
        this.numeProfesor = numeProfesor;
    }

    public Student() {

    }

    /**
     *
     * @return numele type String
     */
    public String getNumele() {
        return numele;
    }

    /**
     *
     * @param numele String
     */
    public void setNumele(String numele) {
        this.numele = numele;
    }

    /**
     *
     * @return grupa type Integer
     */
    public Integer getGrupa() {
        return grupa;
    }

    /**
     *
     * @param grupa type Integer
     */
    public void setGrupa(Integer grupa) {
        this.grupa = grupa;
    }

    /**
     *
     * @return email type String
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email type Integer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return numeProfesor type String
     */
    public String getNumeProfesor() {
        return numeProfesor;
    }

    /**
     *
     * @param numeProfesor type String
     */
    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }


    /**
     *
     * @return idStudent type Integer
     */
    @Override
    public Integer getID() {
        return this.idStudent;
    }

    /**
     *
     * @param id type Integer
     */
    @Override
    public void setID(Integer id) {
        this.idStudent = id;
    }

    /**
     *
     * @return String format for Student
     */
    @Override
    public String toString() {
        return idStudent +" "+
                numele + " "+
                grupa + " "+
                email + " "+
                numeProfesor;
    }
}
