package Controller;

import Email.GmailQuickstart;
import common.Domain.Student;
import common.Service.ServiceStudent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainGui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class MailController  {



    private ServiceStudent serviceStudent;

    /**
     * PrimaryStage for MailWindow
     */
    private Stage primaryStage;

    @FXML private ComboBox<String> numeStudComboBox;

    @FXML private TextField subjectTextField;


    /**
     * Seteaza un mesaj implicit pentru mail.
     * Initializeaza AutoCompleteComboBox-ul cu datele studentilor
     */
    @FXML
    public void initialize() {
        subjectTextField.setText("Situatie Map");

    }

    /**
     * Daca nu ai completat toate campurile iti afiseaza fereastra cu eroarea
     * Ia datele studentului si mesajul, trimite mail cu GmailQuickStart
     * @throws IOException from Window
     * @throws GeneralSecurityException From Email
     */
    @FXML
    public void handleSendMail() throws IOException, GeneralSecurityException {
        if(numeStudComboBox.getSelectionModel().isEmpty() || subjectTextField.getText().isEmpty())
            ErrorView.showErrorMessage(null, "Nu ai completat toate campurile!");
        else
        {
            String idStudent = numeStudComboBox.getSelectionModel().getSelectedItem().split("[()]")[1];

            Student s = serviceStudent.findStudent(Integer.parseInt(idStudent));
            try {
                GmailQuickstart.sendMail(s.getEmail(), s.getNumele(), subjectTextField.getText());
                ErrorView.showMessage(null, Alert.AlertType.INFORMATION, "DONE", "Mail trimis cu succes!");
            }catch (FileNotFoundException e){
                ErrorView.showMessage(null, Alert.AlertType.ERROR, "ERROR", "Studentul nu are note!");
            }
        }

    }


    /**
     * Revine la fereastra de login
     * @throws IOException from Window
     */
    @FXML
    public void handleBackButton()throws IOException {
        BackButtonClass.backButtonMethod(MainGui.startView,primaryStage);
    }

    /**
     * Curata toate campurile de datele introduse
     */
    public void handleClear() {
        numeStudComboBox.getSelectionModel().clearSelection();
        numeStudComboBox.setValue("");
        subjectTextField.clear();
    }

    /**
     * Seteaza service-ul pentru a avea acces la studenti
     * @param serviceStudent ServiceStudent
     */
    void setServiceNota(ServiceStudent serviceStudent) {
        this.serviceStudent = serviceStudent;
        ComboBoxClass.initComboBoxAutoComplete(serviceStudent, numeStudComboBox);
    }

    /**
     * Seteaza stage-ul de baza
     * @param stage Stage
     */
    public void setStage(Stage stage){
        this.primaryStage = stage;
    }

}
