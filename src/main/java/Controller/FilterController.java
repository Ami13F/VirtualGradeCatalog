package Controller;

import Utils.Observer.Observer;
import common.Domain.NotaDTO;
import common.Service.ServiceNota;
import common.Service.ServiceStudent;
import common.Service.ServiceTema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterController implements Observer {
    ServiceNota serviceNota;
    ServiceStudent serviceStudent;
    ServiceTema serviceTema;

    Stage primaryStage;

    static String startView = "/views/startView.fxml";

    ObservableList<NotaDTO> model = FXCollections.observableArrayList();

    @FXML
    private TableView<NotaDTO> tableView;


    @FXML
    private TableColumn<NotaDTO, String> tableColumnNumeStud;

    @FXML
    private TableColumn<NotaDTO, Integer> tableColumnGrupa;

    @FXML
    private TableColumn<NotaDTO, String> tableColumnNumeProf;

    @FXML
    private TableColumn<NotaDTO, String> tableColumnTema;
    @FXML
    private TableColumn<NotaDTO, Float> tableColumnNota;


    @FXML
    private TextField numeStudentTextField;

    @FXML
    private ComboBox temaComboBox;

    @FXML
    private ComboBox grupaComboBox;

    @FXML
    public void initialize() {

        tableColumnNumeStud.setCellValueFactory(new PropertyValueFactory<>("Nume"));
        tableColumnGrupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        tableColumnNumeProf.setCellValueFactory(new PropertyValueFactory<>("numeProf"));
        tableColumnTema.setCellValueFactory(new PropertyValueFactory<>("Tema"));
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));



        tableView.setItems(model);
        model.setAll(getNotaDTOList());
        handleFiltrare();

    }

    public void handleFiltrare(){
        numeStudentTextField.textProperty().addListener(x -> handleFilter());
        temaComboBox.valueProperty().addListener(x -> handleFilter());
        grupaComboBox.valueProperty().addListener(x -> handleFilter());
    }

    private void handleFilter() {

        Predicate<NotaDTO> p1 = m -> {
            if (numeStudentTextField.getText().isEmpty())
                return true;
            return m.getNume().contains(numeStudentTextField.getText());
        };

        Predicate<NotaDTO> p2 = m ->
        {
            if (grupaComboBox.getSelectionModel().isEmpty())
                return true;
            else
                return m.getGrupa().toString().contains(grupaComboBox.getValue().toString());
        };
        Predicate<NotaDTO> p3 = m -> {
            if (temaComboBox.getSelectionModel().isEmpty())
                return true;
            return m.getTema().equals(temaComboBox.getValue().toString());
        };


        model.setAll(getNotaDTOList()
                .stream()
                .filter(p1.and(p2).and(p3))
                .collect(Collectors.toList()));
    }

    @FXML
    public void handleBackButton()throws IOException {
        BackButtonClass.backButtonMethod(startView,primaryStage);
    }

    private List<NotaDTO> getNotaDTOList() {
        return serviceNota.getAllNota()
                .stream()
                .map((nota) -> {
                    Integer grupa = nota.getStudent().getGrupa();
                    String nume= nota.getStudent().getNumele();
                    String prof = nota.getStudent().getNumeProfesor();
                    String tema = nota.getTema().getDescriere();
                    Float notaValue = nota.getNota();
                    return new NotaDTO(nume, grupa, prof, tema, notaValue);
                })
                .collect(Collectors.toList());

    }

    public void handleClear() {
        clearFilds();
    }

    public void clearFilds() {
        numeStudentTextField.clear();
        temaComboBox.getSelectionModel().clearSelection();
        grupaComboBox.getSelectionModel().clearSelection();

    }

    public void setServiceNota(ServiceNota serviceNota, ServiceStudent servStud, ServiceTema serviceTema) {

        this.serviceNota = serviceNota;
        this.serviceStudent = servStud;
        this.serviceTema = serviceTema;
        serviceNota.addObserver(this);
        servStud.addObserver(this);

        initModel();


        ComboBoxClass.initComboBoxGrupa(grupaComboBox);
        ComboBoxClass.initComboBoxTema(temaComboBox);

        tableView.getSelectionModel().selectedItemProperty().addListener((
                (observable, oldValue, newValue) -> {
                    if (newValue != null)

                        setSelectedItem(newValue);
                }));
    }
    private void setSelectedItem(NotaDTO n) {

    }

    private void initModel() {
        List<NotaDTO> list = new ArrayList<>(getNotaDTOList());
        model.setAll(list);
    }

    public void setStage(Stage stage){
        this.primaryStage = stage;
    }

    @Override
    public void update() {
        model.setAll(new ArrayList<>(getNotaDTOList()));
    }
}
