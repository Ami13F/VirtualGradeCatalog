package Controller;

import common.Service.ServiceNota;
import common.Service.ServiceStudent;
import common.Service.ServiceTema;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GradeController {

    Stage stage;
    ServiceStudent serviceStudent;
    ServiceTema serviceTema;
    ServiceNota serviceNota;
    BorderPane borderPane;

    String addView = "/views/addView.fxml";
    String gradeView = "/views/gradeView.fxml";
    String filterView = "/views/filterView.fxml";
    String raportView = "/views/raportView.fxml";
    String mailView = "/views/mailView.fxml";

    @FXML
    MenuItem addMenuItem;
    @FXML
    MenuItem filtrareMenuItemId;


    public void addMethod(Stage stage)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(addView));
        AnchorPane layout =  loader.load();
        Scene scene = new Scene(layout);

        NotaController controller = loader.getController();
        controller.setStage(stage);
        controller.setServiceNota(serviceNota,serviceStudent,serviceTema);
        stage.setScene(scene);
        borderPane.setCenter(layout);
    }

    public void mailMethod(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(mailView));
        AnchorPane layout =  loader.load();
        Scene scene = new Scene(layout);

        MailController controller = loader.getController();
        controller.setStage(stage);
        controller.setServiceNota(serviceStudent);
        stage.setScene(scene);
        borderPane.setCenter(layout);
    }

    public void filterMethod()throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(filterView));
        AnchorPane layout = loader.load();
        Scene scene = new Scene(layout);

        FilterController controller = loader.getController();
        controller.setStage(stage);
        controller.setServiceNota(serviceNota, serviceStudent, serviceTema);
        stage.setScene(scene);
        borderPane.setCenter(layout);
    }


    public void raportsMethod()throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(raportView));
        AnchorPane layout =  loader.load();
        Scene scene = new Scene(layout);

        RaportMedieLaboratorController controller = loader.getController();
        controller.setStage(stage,serviceStudent,serviceTema,serviceNota);
        stage.setScene(scene);
        borderPane.setCenter(layout);
    }

    public void handleAddMenuItem() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(gradeView));
        BorderPane layout =  loader.load();
        Scene scene = new Scene(layout);
        GradeController controller = loader.getController();
        controller.setLayout(layout);
        controller.setStage(stage,serviceStudent,serviceTema,serviceNota);
        controller.addMethod(stage);
        stage.setScene(scene);

    }
    public void handleMailMenuItem() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(gradeView));
        BorderPane layout =  loader.load();
        Scene scene = new Scene(layout);
        GradeController controller = loader.getController();
        controller.setLayout(layout);
        controller.setStage(stage,serviceStudent,serviceTema,serviceNota);
        controller.mailMethod(stage);
        stage.setScene(scene);

    }

    public void handleFilterMenuItem() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(gradeView));
        BorderPane layout =  loader.load();
        Scene scene = new Scene(layout);

        GradeController controller = loader.getController();
        controller.setLayout(layout);
        controller.setStage(stage,serviceStudent,serviceTema,serviceNota);
        controller.filterMethod();
        stage.setScene(scene);

    }



    public void handleRaportMenuItem()throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(gradeView));
        BorderPane layout =  loader.load();
        Scene scene = new Scene(layout);

        GradeController controller = loader.getController();
        controller.setLayout(layout);
        controller.setStage(stage,serviceStudent,serviceTema,serviceNota);
        controller.raportsMethod();
        stage.setScene(scene);
    }


    public void setStage(Stage stage, ServiceStudent serviceStudent, ServiceTema serviceTema, ServiceNota serviceNota) throws IOException {
        this.stage = stage;
        this.serviceStudent = serviceStudent;
        this.serviceTema = serviceTema;
        this.serviceNota = serviceNota;

    }


    public void setLayout(BorderPane bd){
        this.borderPane = bd;
    }
}
