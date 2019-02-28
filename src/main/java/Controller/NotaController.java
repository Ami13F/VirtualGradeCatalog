package Controller;

import Exceptions.ValidationException;
import Utils.Observer.Observer;
import common.Domain.Nota;
import common.Domain.NotaDTO;
import common.Domain.Student;
import common.Domain.Tema;
import common.Service.ServiceNota;
import common.Service.ServiceStudent;
import common.Service.ServiceTema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainGui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NotaController implements Observer {


    private ServiceNota serviceNota;
    private ServiceStudent serviceStudent;
    private ServiceTema serviceTema;

    private Stage primaryStage;


    private static ObservableList<NotaDTO> model = FXCollections.observableArrayList();

    @FXML private TableView<NotaDTO> tableView;

    @FXML private TableColumn<NotaDTO, String> tableColumnNumeStud;
    @FXML private TableColumn<NotaDTO, Integer> tableColumnGrupa;
    @FXML private TableColumn<NotaDTO, String> tableColumnNumeProf;
    @FXML private TableColumn<NotaDTO, String> tableColumnTema; //t1 //t2
    @FXML private TableColumn<NotaDTO, Float> tableColumnNota;

    @FXML private CheckBox motivatCheckBox;

    @FXML private ComboBox temaComboBox;
    @FXML private ComboBox dataPredareComboBox;
    @FXML private ComboBox<String> numeStudComboBox;

    @FXML private TextArea feedbackTextField;

    @FXML private TextField notaTextField;


    @FXML
    public void initialize() {

        tableColumnNumeStud.setCellValueFactory(new PropertyValueFactory<>("Nume"));
        tableColumnGrupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        tableColumnNumeProf.setCellValueFactory(new PropertyValueFactory<>("numeProf"));
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        tableColumnTema.setCellValueFactory(new PropertyValueFactory<>("Tema"));


        tableView.setItems(model);
        model.setAll(getNotaDTOList());

    }


    static void handleFilter(String t){

        Predicate<NotaDTO> p1 = m -> {
            if (t.isEmpty())
                return true;
            return m.getNume().contains(t);
        };
        model.setAll(getNotaDTOList()
                .stream()
                .filter(p1)
                .collect(Collectors.toList()));
    }

    @FXML
    public void handleBackButton()throws IOException{
        String startView = MainGui.startView;
        BackButtonClass.backButtonMethod(startView,primaryStage);
    }

    public void handleAdd() {

        Student s;
        Tema t;
        if (numeStudComboBox.getSelectionModel().isEmpty()
                || temaComboBox.getSelectionModel().isEmpty() || dataPredareComboBox.getSelectionModel().isEmpty()) {
            ErrorView.showErrorMessage(null, "Nu ai completat toate campurile!");
            return;
        }
        try {
            String idStudent = numeStudComboBox.getSelectionModel().getSelectedItem()
                    .split("[()]")[1];

            // Integer grupa = Integer.parseInt(grupaComboBox.getValue().toString());

            s = serviceStudent.findStudent(Integer.parseInt(idStudent));

            String tema = temaComboBox.getValue().toString();

            t = serviceTema.findTemaDupaDescriere(tema);


            Integer dataPredare = Integer.parseInt(dataPredareComboBox.getValue().toString());
            Float notaValue = Float.parseFloat(notaTextField.getText());


            Nota nota = new Nota(t, s, notaValue, dataPredare);
            Boolean motivat = motivatCheckBox.isSelected();

            notaValue = nota.calcNota(notaValue, motivat);
            nota.setNota(notaValue);

            Nota rez = serviceNota.findNota(nota.getID()); //daca e null  nu exista

            String feedBack = feedbackTextField.getText();
            if (feedBack.equals("")) {
                feedBack = "-";
            }

            if (nota.getPenalizare() == 9) {
                feedBack = "Ai intarziat mai mult \n de 2 saptamani." +"\n"+
                        "      Ai 1,sorry.";

            } else if (nota.getPenalizare() > 0) {
                feedBack = "Ai intarziat " + nota.getPenalizare() + " saptamana";
            }
            feedbackTextField.setText(feedBack);

            if (rez == null) {
                confirmWindow(nota, feedBack);
                feedbackTextField.clear();
            } else
                ErrorView.showErrorMessage(null, "Nota exista");


        } catch (ValidationException e) {
            ErrorView.showErrorMessage(null, e.what());
        } catch (NumberFormatException e) {
            ErrorView.showErrorMessage(null, "Date incorecte!");
            e.getMessage();
        } catch (IOException e) {
            ErrorView.showErrorMessage(null, "File not find!");
            e.getMessage();
        }catch (ArrayIndexOutOfBoundsException e) {
            ErrorView.showErrorMessage(null, "Selecteaza un student din combo box!");
            e.getMessage();
        }
    }

    private void confirmWindow(Nota nota, String feedback) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/confirmWindowView.fxml"));
        AnchorPane layout = loader.load();
        Scene scene = new Scene(layout);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);

        ConfirmareWindowController controller = loader.getController();

        controller.setService(serviceNota, nota, feedback);
        controller.setLabel();
        controller.setStage(stage);
        stage.show();

        // boolean confirm = controller.handleOk();
    }

    @FXML
    private void handleClickOut(){
        tableView.getSelectionModel().clearSelection();
        tableView.refresh();
    }

    @FXML
    public void handleDelete() {
        NotaDTO selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {

            Student s = serviceStudent.findStudentDupaNumeSiGrupa(selected.getNume(), selected.getGrupa());
            Tema t = serviceTema.findTemaDupaDescriere(selected.getTema());
            Nota notaDel = serviceNota.deleteNota(t.getID().toString() + s.getID().toString());
            if (notaDel != null) {
                ErrorView.showMessage(null, Alert.AlertType.INFORMATION, "Delete", "Nota a fost stearsa cu succes!");
                model.setAll(new ArrayList<>(getNotaDTOList()));
            }
        } else ErrorView.showErrorMessage(null, "Nu ati selectat nici o nota!");
    }

    private void clearFilds() {
        notaTextField.clear();
        temaComboBox.getSelectionModel().clearSelection();
        numeStudComboBox.setValue("");
        dataPredareComboBox.getSelectionModel().clearSelection();
        numeStudComboBox.getSelectionModel().clearSelection();
        feedbackTextField.clear();

    }

    public void handleClear() {
        clearFilds();
    }


    private static List<NotaDTO> getNotaDTOList() {
        return ServiceNota.getAllNota()
                .stream()
                .map((nota) -> {
                    String numeStudent = nota.getStudent().getNumele();
                    Integer grupa = nota.getStudent().getGrupa();
                    String tema = nota.getTema().getDescriere();
                    String numeProf = nota.getStudent().getNumeProfesor();
                    Float notaValue = nota.getNota();
                    return new NotaDTO(numeStudent, grupa, numeProf, tema, notaValue);
                })
                .collect(Collectors.toList());

    }


    void setServiceNota(ServiceNota serviceNota, ServiceStudent servStud, ServiceTema serviceTema) {

        this.serviceNota = serviceNota;
        this.serviceTema = serviceTema;
        this.serviceStudent = servStud;
        serviceNota.addObserver(this);
        servStud.addObserver(this);

        initModel();

        //lab-ul curent trebuie predat pana in sapt 12

        //  ComboBoxClass.initComboBoxGrupa(grupaComboBox);
        ComboBoxClass.initComboBoxTema(temaComboBox);
        ComboBoxClass.initDataPredareComboBox(dataPredareComboBox);

        ComboBoxClass.initComboBoxAutoComplete(serviceStudent, numeStudComboBox);

        //grupaComboBox.setDisable(true);
        temaComboBox.getSelectionModel().select(12);
        dataPredareComboBox.getSelectionModel().select(13);

    }


    @Override
    public void update() {
        model.setAll(new ArrayList<>(getNotaDTOList()));
    }


    private void initModel() {
        List<NotaDTO> list = new ArrayList<>(getNotaDTOList());
        model.setAll(list);
    }

    public void setStage(Stage stage){
        this.primaryStage = stage;
    }

}
