package Controller;

import Exceptions.ValidationException;
import Utils.Observer.Observer;
import ValidatorPackage.StudentValidator;
import common.Domain.NotaDTO;
import common.Domain.Student;
import common.Service.ServiceStudent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentController implements Observer {

    private String startView;

    private Stage dialogStage;


    private ServiceStudent serviceStud;

    private ObservableList<Student> model = FXCollections.observableArrayList();

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student,Integer> tableColumnId;

    @FXML
    private TableColumn<Student,String> tableColumnNume;

    @FXML
    private TableColumn<Student,Integer> tableColumnGrupa;

    @FXML
    private TableColumn<Student,String> tableColumnEmail;

    @FXML
    private TableColumn<Student,String> tableColumnNumeProf;

    @FXML
    private TextField numeStudentTextField;

    @FXML
    private ComboBox grupaComboBox;

    @FXML
    private TextField emailStudentTextField;

    @FXML
    private TextField currentPageTextField;

    @FXML private ComboBox numeProfComboBox;

    @FXML private Label currentPageLabel;

    @FXML private Label maxNrOfPageLabel;

    @FXML private Button btnDelete;
    @FXML private Button backButton;
    @FXML private TextField numeStudentFilterTextField;



    @FXML
    public void initialize(){
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tableColumnNume.setCellValueFactory(new PropertyValueFactory<>("numele"));
        tableColumnGrupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnNumeProf.setCellValueFactory(new PropertyValueFactory<>("numeProfesor"));


        currentPageTextField.setVisible(false);
        numeStudentFilterTextField.textProperty().addListener(x -> handleFind());

        infoButtons();

        editaTableColumn();
        tableView.setItems(model);
    }

    //Tooltip for info
    private void infoButtons(){
        //info for delete
        Tooltip toolTipDelete = new Tooltip("Selecteaza un student din tabel");
        btnDelete.setTooltip(toolTipDelete);

        //info for home
        Tooltip toolTipBack = new Tooltip("Go back home");
        backButton.setTooltip(toolTipBack);

    }
    @FXML
    private void handleFind(){
        Predicate<Student> p1 = m -> {
            if (numeStudentFilterTextField.getText().isEmpty())
                return true;
            return m.getNumele().contains(numeStudentFilterTextField.getText());
        };
        if(currentPageTextField.getText().equals("")){
            getListaStudentiFiltrata(1,p1);
        }else
            getListaStudentiFiltrata(Integer.parseInt(currentPageLabel.getText()),p1);
    }

    private void editaTableColumn(){
        tableView.setEditable(true);
        tableColumnNume.setEditable(true);
        tableColumnGrupa.setEditable(true);
        tableColumnEmail.setEditable(true);
        tableColumnNumeProf.setEditable(true);
        tableColumnNume.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnGrupa.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                try{
                    return Integer.parseInt(string);
                }catch(NumberFormatException | NullPointerException e){
                    return -1;
                }
            }
        }));
        tableColumnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnNumeProf.setCellFactory(TextFieldTableCell.forTableColumn());

        tableColumnNume.setOnEditCommit(t-> {
            if(!t.getNewValue().equals("")) {
                t.getRowValue().setNumele(t.getNewValue());
                handleUpdateStudent(t.getRowValue());

            }else {
                Student s = new Student(t.getRowValue().getID(),t.getNewValue(),t.getRowValue().getGrupa(),t.getRowValue().getEmail(),t.getRowValue().getNumeProfesor());
                handleUpdateStudent(s);
                setCurrentPage();
                handleClickOut();
            }
        });
        tableColumnGrupa.setOnEditCommit(t->{
            if(!(t.getNewValue() == null || t.getNewValue()<221 || t.getNewValue()>227)){
                t.getRowValue().setGrupa(t.getNewValue());
                handleUpdateStudent(t.getRowValue());
            }else{
                Student s = new Student(t.getRowValue().getID(),t.getRowValue().getNumele(),t.getNewValue(),t.getRowValue().getEmail(),t.getRowValue().getNumeProfesor());
                handleUpdateStudent(s);
                setCurrentPage();
                handleClickOut();
            }

        });
        tableColumnEmail.setOnEditCommit(t->{
            if(StudentValidator.validateEmail(t.getNewValue()).equals("")){
                t.getRowValue().setEmail(t.getNewValue());
                handleUpdateStudent(t.getRowValue());


            }else{
                Student s = new Student(t.getRowValue().getID(),t.getRowValue().getNumele(),t.getRowValue().getGrupa(),t.getNewValue(),t.getRowValue().getNumeProfesor());
                 handleUpdateStudent(s);
                setCurrentPage();
                handleClickOut();
            }

        });
        tableColumnNumeProf.setOnEditCommit(t->{
            if(StudentValidator.validateProfesor(t.getNewValue()).equals("")){
                t.getRowValue().setNumeProfesor(t.getNewValue());
                handleUpdateStudent(t.getRowValue());

            }else{
                Student s = new Student(t.getRowValue().getID(),t.getRowValue().getNumele(),t.getRowValue().getGrupa(),t.getRowValue().getEmail(),t.getNewValue());
                handleUpdateStudent(s);
                setCurrentPage();
                handleClickOut();
            }
        });

    }


    @FXML
    private void handleBackButtonPage(){
        if(serviceStud.getPage()>=1 && serviceStud.getPage()<=serviceStud.maxNumberOfPage()){
            currentPageTextField.setText(String.valueOf(serviceStud.getPage()-1));
            setCurrentPage();
        }
    }
    @FXML
    private void handleNextButtonPage(){
        if(serviceStud.getPage()>=0 && serviceStud.getPage()<serviceStud.maxNumberOfPage()){
            currentPageTextField.setText(String.valueOf(serviceStud.getPage()+1));
            setCurrentPage();
        }
    }


    @FXML
    private void handleClickOut(){
        setCurrentPage();

        tableView.getSelectionModel().clearSelection();
        tableView.refresh();
    }

    private void setCurrentPage(){
        if(currentPageTextField.getText().equals("") ||  Integer.parseInt(currentPageTextField.getText()) <= 0 ||  Integer.parseInt(currentPageTextField.getText()) > serviceStud.maxNumberOfPage() ) {
            currentPageLabel.setVisible(true);
            currentPageTextField.setVisible(false);
            return;
        }
        if(Integer.parseInt(currentPageTextField.getText()) <= serviceStud.maxNumberOfPage()  ){
            currentPageLabel.setVisible(true);
            currentPageLabel.setText(currentPageTextField.getText());
            currentPageTextField.setVisible(false);
            getListaStudenti(Integer.parseInt(currentPageTextField.getText()));
        }
    }


    @FXML
    public void handleCurrentPage(){
        currentPageLabel.setVisible(false);
        currentPageTextField.setVisible(true);
    }

    private void getListaStudenti(int currentPage){
        serviceStud.setPage(currentPage-1);
        List<Student> lista = serviceStud.getNext();

        if(lista!=null){
            model.setAll(lista);
        }
        if(lista==null && serviceStud.getAll().isEmpty())
            model.setAll();
    }

    private void getListaStudentiFiltrata(int currentPage,Predicate<Student> p1){
        serviceStud.setPage(currentPage-1);
        List<Student> lista = serviceStud.getNext();

        if(lista!=null){
            model.setAll(lista.stream()
                    .filter(p1)
                    .collect(Collectors.toList()));
        }
        if(lista==null && serviceStud.getAll().isEmpty())
            model.setAll();
    }


    private void reloadModel(Integer pageNumber){
        serviceStud.setPage(pageNumber);
        getListaStudenti(pageNumber);
    }


    @Override
    public void update() {

        if(currentPageTextField.getText().isEmpty()){
            reloadModel(1);
        }else
        reloadModel(Integer.parseInt(currentPageTextField.getText()));
    }


    /**
     * @param serviceStud Service Student
     *
     * @param startView View pt LogIn
     */
    void setServiceStud(ServiceStudent serviceStud,String startView){
        this.startView = startView;
        this.serviceStud = serviceStud;

        serviceStud.addObserver(this);
        initModel();

        ComboBoxClass.initComboBoxGrupa(grupaComboBox);
        ComboBoxClass.initComboBoxProf(numeProfComboBox);

    }


    private void initModel(){
        maxNrOfPageLabel.setText("/"+serviceStud.maxNumberOfPage().toString());
        reloadModel(0);

        currentPageTextField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                handleClickOut();
            }
        });
    }


    @FXML
    public void handleDeleteStudent() {
        Student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Student deleted = serviceStud.deleteStudent(selected.getID());
            if (null != deleted)
                ErrorView.showMessage(null, Alert.AlertType.INFORMATION, "Delete", "Studentul a fost sters cu succes!");
        } else ErrorView.showErrorMessage(null, "Nu ati selectat nici un student!");
    }


    @FXML
    public void handleClearFields() {
        clearFields();
    }


    private void clearFields() {
        numeStudentTextField.setText("");
        grupaComboBox.getSelectionModel().clearSelection();
        emailStudentTextField.setText("");
        numeProfComboBox.getSelectionModel().clearSelection();
        numeStudentFilterTextField.clear();
    }


    private void handleUpdateStudent(Student s) {

        try {
            serviceStud.updateStudent(s);
        }catch(ValidationException e){
            handleClickOut();
            update();
            ErrorView.showErrorMessage(null, e.what());

        }catch(NumberFormatException e){
            ErrorView.showErrorMessage(null, "Date incorecte!");
            e.getMessage();
        }
    }


    @FXML
    public void handleAddStudent() {
        try {
            //Integer id = Integer.parseInt(idStudentTextField.getText());
            String nume = numeStudentTextField.getText();
            String delenume = numeStudentTextField.getText();
            Integer grupa = Integer.parseInt(grupaComboBox.getValue().toString());
            String email = emailStudentTextField.getText();
            String numeProf = numeProfComboBox.getValue().toString();
            Student s = new Student( nume, grupa, email, numeProf);
            serviceStud.saveStudent(s);
            ErrorView.showMessage(null, Alert.AlertType.INFORMATION, "Adaugare", "Studentul a fost adaugat cu succes!");
        }catch(ValidationException e){
            ErrorView.showErrorMessage(null, e.what());
        }catch(NumberFormatException e ){
            ErrorView.showErrorMessage(null, "Nu ai completat corect!");
            e.getMessage();
        }catch(NullPointerException e){
            ErrorView.showErrorMessage(null, "Nu ai completat corect!");

        }
    }


    @FXML
    public void handleBackButton() throws IOException {
        BackButtonClass.backButtonMethod(startView,dialogStage);
    }


    void setStage(Stage stage){
        dialogStage = stage;
    }

}
