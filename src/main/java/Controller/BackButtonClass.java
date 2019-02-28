package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BackButtonClass {


    public static void backButtonMethod(String startView, Stage dialogStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BackButtonClass.class.getResource(startView));
        AnchorPane layout = loader.load();
        Scene scene = new Scene(layout);

        dialogStage.setScene(scene);
        StartController controller = loader.getController();
        controller.setService();
        controller.setStage(dialogStage);
        dialogStage.show();
    }
}
