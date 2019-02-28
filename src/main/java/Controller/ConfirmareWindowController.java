package Controller;

import Exceptions.ValidationException;
import common.Domain.Nota;
import common.Service.ServiceNota;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmareWindowController {

    private Nota nota;
    private ServiceNota serviceNota;
    private String feedback;


    @FXML
    private Stage stage;


    @FXML
    private Label dateNota;

    /**
     * Daca utilizatorul apasa butonul ok, iar datele introduse sunt corecte
     * nota va fi salvata
     * @throws IOException from stage
     */
    public void handleOk()throws IOException {
        try{
        serviceNota.saveNota(nota,feedback);
        }catch(ValidationException v){
            ErrorView.showErrorMessage(null, v.what());
        }
        stage.close();
    }

    /**
     * Anuleaza salvarea notei
     */
    public void handleCancel(){
        stage.close();

    }

    void setService(ServiceNota servNota, Nota no, String fb){
        serviceNota = servNota;
        nota = no;
        feedback = fb;
    }

    void setLabel(){
        String motivat = "DA";
        if(!nota.getMotivat()){
            motivat = "NU";
        }
        dateNota.setText("Descriere: "+nota.getTema().getDescriere()+"\n"+
                " Deadline: "+nota.getTema().getDeadline().toString()+"\n"+
                "DataPredare: "+ nota.getDataPredareTema().toString()+"\n"+
                "Nume: "+nota.getStudent().getNumele()+"\n"+
                "Grupa: " + nota.getStudent().getGrupa().toString()+"\n"+
                "Nota: " + nota.getNota().toString()+"\n"+
                "Motivat: " +motivat+
                " \nFeedback: "+feedback);

    }

    public void setStage(Stage s){
        this.stage = s;
    }

}
