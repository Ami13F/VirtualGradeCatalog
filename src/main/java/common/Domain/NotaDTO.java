package common.Domain;


public class NotaDTO {

    private String nume;
    private Integer grupa;
    private String numeProf;
    private String tema;
    private Float nota;


    public NotaDTO(String nume,Integer grupa,String numeProf,String tema,Float nota){
        this.nume = nume;
        this.grupa = grupa;
        this.numeProf = numeProf;
        this.tema = tema;
        this.nota = nota;

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getGrupa() {
        return grupa;
    }

    public void setGrupa(Integer grupa) {
        this.grupa = grupa;
    }

    public String getNumeProf() {
        return numeProf;
    }

    public void setNumeProf(String numeProf) {
        this.numeProf = numeProf;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }
}
