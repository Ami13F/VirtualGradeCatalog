package common.Domain;


public class NotaDTOStudent {
    Float notaValue ;
    int deadline;
    int predare;
    int primire;
    String tema;

    public NotaDTOStudent(Float notaValue, int deadline, int predare, int primire,String tema) {
        this.notaValue = notaValue;
        this.deadline = deadline;
        this.predare = predare;
        this.primire = primire;
        this.tema = tema;
    }

    public Float getNotaValue() {
        return notaValue;
    }

    public void setNotaValue(Float notaValue) {
        this.notaValue = notaValue;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getPredare() {
        return predare;
    }

    public void setPredare(int predare) {
        this.predare = predare;
    }

    public int getPrimire() {
        return primire;
    }

    public void setPrimire(int primire) {
        this.primire = primire;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
