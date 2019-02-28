package main;

import Controller.StartController;
import common.Repository.RepositoryParola;
import common.Service.ServiceParola;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class MainGui extends Application{


    public static final ServiceParola serviceParola = new ServiceParola(
            new RepositoryParola("src/main/java/DataAll/DateUser/DateProfesor.txt"),
            new RepositoryParola("src/main/java/DataAll/DateUser/DateSecretar.txt"),
            new RepositoryParola("src/main/java/DataAll/DateUser/DateStudent.txt"));


    public static final String startView = "/views/startView.fxml";


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(startView));
        AnchorPane layout =  loader.load();
        Scene scene = new Scene(layout);
        primaryStage.setResizable(false);


        primaryStage.getIcons().add(new Image("Images/appIcon.png"));
        primaryStage.setTitle("Aplicatie Profesor");

        primaryStage.setScene(scene);
        StartController controller = loader.getController();
        controller.setService();
        controller.setStage(primaryStage);

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}