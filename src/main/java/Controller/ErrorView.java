package Controller;


import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class ErrorView {
    static void showMessage(Stage owner, Alert.AlertType type, String header, String text){
        Alert message=new Alert(type);
        message.setHeaderText(header);
        message.setContentText(text);
        message.initOwner(owner);

        message.getDialogPane().setStyle(
                "-fx-background-color: derive(-fx-color,-40%),\n" +
                        "    derive(-fx-color,100%),\n" +
                        "    linear-gradient(to bottom,\n" +
                        "            #3385ff, #ffffff);");


        message.showAndWait();
    }

    static void showErrorMessage(Stage owner, String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.initOwner(owner);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);

        message.getDialogPane().setStyle(
                "-fx-background-color: derive(-fx-color,-40%),\n" +
                        "    derive(-fx-color,100%),\n" +
                        "    linear-gradient(to bottom,\n" +
                        "            #3385ff, #ffffff);");

        message.showAndWait();

    }
}
