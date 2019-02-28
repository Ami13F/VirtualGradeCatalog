package Controller;


import Exceptions.ValidationException;
import ValidatorPackage.NotaValidator;
import ValidatorPackage.StudentValidator;
import ValidatorPackage.TemeValidator;
import common.Domain.Nota;
import common.Domain.Student;
import common.Domain.Tema;
import common.Domain.User;
import common.Repository.PageCrudRepository;
import common.Repository.RepositoryXML.NotaRepositoryXML;
import common.Repository.RepositoryXML.StudentRepositotyXML;
import common.Repository.RepositoryXML.TemaRepositoryXML;
import common.Service.ServiceNota;
import common.Service.ServiceParola;
import common.Service.ServiceStudent;
import common.Service.ServiceTema;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.MainGui;

import java.io.IOException;

public class StartController {

    private String fileXmlNota = "src/main/java/DataAll/DataRun/Nota.xml";
    private String fileXmlStud = "src/main/java/DataAll/DataRun/Studenti.xml";
    private String fileXmlTema = "src/main/java/DataAll/DataRun/Tema.xml";

    private PageCrudRepository<Integer, Student> studRepo;
    private PageCrudRepository<Integer, Tema> temeRepo;
    private PageCrudRepository<String, Nota> noteRepo;

    private ServiceStudent serviceStudent;
    private ServiceTema serviceTema;
    private ServiceNota serviceNota;
    private ServiceParola serviceParola = MainGui.serviceParola;

    private String startView = MainGui.startView;
    private static final String studentView = "/views/studentView.fxml";
    private String gradeView = "/views/gradeView.fxml";
    private String studentGradesView = "/views/studentGradeView.fxml";
    private Stage primaryStage;

    @FXML TextField userTextField;
    @FXML TextField passTextField;

    @FXML Button logBtn;

    @FXML
    public void initialize(){
        logBtn.setDefaultButton(true);
    }



    public void handleLogBtn() throws IOException{
        if(userTextField.getText().equals("") || passTextField.getText().equals("")){
            ErrorView.showErrorMessage(null,"Nu ai completat toate campurile!");
            return;
        }
        try{
        User user = new User(userTextField.getText(),passTextField.getText());
        String userType = serviceParola.findUser(user);
            switch (userType) {
                case "Profesor":
                    gradeView(primaryStage);
                    break;
                case "Secretar":
                    studentView(primaryStage);
                    break;
                case "Student":
                    studentGradesView(primaryStage);
                    break;
            }
        }catch(ValidationException v) {
            ErrorView.showErrorMessage(null, v.what());
        }

    }

    private void gradeView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(gradeView));
        BorderPane layout =  loader.load();
        Scene scene = new Scene(layout);

        GradeController controller = loader.getController();
        controller.setLayout(layout);
        controller.setStage(stage,serviceStudent,serviceTema,serviceNota);
        controller.addMethod(stage);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    private void studentView(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(studentView));
        AnchorPane layout =  loader.load();
        Scene scene = new Scene(layout);

        StudentController controller = loader.getController();
        controller.setServiceStud(serviceStudent,startView);

        primaryStage.setScene(scene);
        controller.setStage(primaryStage);
        primaryStage.show();

    }

    private void studentGradesView(Stage primaryStage)throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(studentGradesView));
        AnchorPane layout =  loader.load();
        Scene scene = new Scene(layout);

        StudentGradeController controller = loader.getController();
        controller.setUserName(userTextField.getText());
        controller.setService(serviceStudent,serviceTema,serviceNota);
        primaryStage.setScene(scene);
        controller.setStage(primaryStage);

        primaryStage.show();
    }




    public void setStage(Stage primaryStage){
            this.primaryStage = primaryStage;
    }

    public void setService(){

        studRepo  = new StudentRepositotyXML(new StudentValidator(),fileXmlStud);
        temeRepo = new TemaRepositoryXML(new TemeValidator(),fileXmlTema);
        noteRepo = new NotaRepositoryXML(new NotaValidator(),fileXmlNota);

        serviceStudent = new ServiceStudent(studRepo);
        serviceTema = new ServiceTema(temeRepo);
        serviceNota = new ServiceNota(noteRepo);

    }

}
