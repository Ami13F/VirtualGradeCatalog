package Controller;

import Utils.Observer.Observer;
import common.Domain.NotaDTOStudent;
import common.Service.ServiceNota;
import common.Service.ServiceStudent;
import common.Service.ServiceTema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.MainGui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




public class StudentGradeController implements Observer{


        String userName;

        ServiceNota serviceNota;
        ServiceStudent serviceStudent;
        ServiceTema serviceTema;

        Stage primaryStage;


        ObservableList<NotaDTOStudent> model = FXCollections.observableArrayList();

        @FXML
        private TableView<NotaDTOStudent> tableView;


        @FXML
        private TableColumn<NotaDTOStudent, Float> tableColumnNota;

        @FXML
        private TableColumn<NotaDTOStudent, Integer> tableColumnDeadline;

        @FXML
        private TableColumn<NotaDTOStudent, Integer> tableColumnDataPredare;

        @FXML
        private TableColumn<NotaDTOStudent, String> tableColumnTema;

        @FXML
        private TableColumn<NotaDTOStudent, Integer> tableColumnDataPrimire;



        @FXML
        public void initialize() {

            tableColumnDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
            tableColumnDataPredare.setCellValueFactory(new PropertyValueFactory<>("predare"));
            tableColumnDataPrimire.setCellValueFactory(new PropertyValueFactory<>("primire"));
            tableColumnNota.setCellValueFactory(new PropertyValueFactory<>("notaValue"));
            tableColumnTema.setCellValueFactory(new PropertyValueFactory<>("Tema"));


            tableView.setItems(model);
            model.setAll(getNotaDTOList());

        }


        @FXML
        public void handleBackButton()throws IOException {
            BackButtonClass.backButtonMethod(MainGui.startView,primaryStage);
        }

        private List<NotaDTOStudent> getNotaDTOList() {
            return serviceNota.getAllNota()
                    .stream()
                    .filter(n->n.getStudent().getNumele().equals(userName))
                    .map((nota) -> {
                        Integer deadline = nota.getTema().getDeadline();
                        Integer predare = nota.getDataPredareTema();
                        String tema = nota.getTema().getDescriere();
                        Integer primire = nota.getTema().getDataPrimire();
                        Float notaValue = nota.getNota();
                        return new NotaDTOStudent(notaValue, deadline,predare, primire,tema);
                    })
                    .collect(Collectors.toList());

        }



        public void setService(ServiceStudent servStud, ServiceTema serviceTema, ServiceNota serviceNota) {

            this.serviceStudent = servStud;
            this.serviceNota = serviceNota;
            this.serviceTema = serviceTema;
            serviceNota.addObserver(this);
            servStud.addObserver(this);

            initModel();



            tableView.getSelectionModel().selectedItemProperty().addListener((
                    (observable, oldValue, newValue) -> {
                        if (newValue != null)
                            setSelectedItem(newValue);
                    }));
        }
        private void setSelectedItem(NotaDTOStudent n) {

        }

        private void initModel() {
            List<NotaDTOStudent> list = new ArrayList<>(getNotaDTOList());
            model.setAll(list);
        }

        public void setStage(Stage stage){
            this.primaryStage = stage;
        }

        public void setUserName(String userName){
            this.userName = userName;
        }

        @Override
        public void update() {
            model.setAll(new ArrayList<>(getNotaDTOList()));
        }


}
