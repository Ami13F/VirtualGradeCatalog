package Controller;

import common.Domain.NotaDTOStudent;
import common.Domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class GrupaFilterController {

    ObservableList<NotaDTOStudent> list = FXCollections.observableArrayList();

    @FXML
    private TableView<NotaDTOStudent> tableViewGrupaFilter;

    @FXML
    private TableColumn grupaTableFiltru;

    @FXML private TableColumn tema1;

    @FXML private TableColumn tema2;

    @FXML private TableColumn tema3;

    @FXML private TableColumn tema4;

    @FXML private TableColumn tema5;

    @FXML private TableColumn tema6;

    @FXML private TableColumn tema7;

    @FXML private TableColumn tema8;

    @FXML private TableColumn tema9;

    @FXML private TableColumn tema10;




    public void setModel(List<NotaDTOStudent> list){
        grupaTableFiltru.setCellValueFactory(new PropertyValueFactory<Student,String>("numeStudent"));
        tema1.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota1"));
        tema2.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota2"));
        tema3.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota3"));
        tema4.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota4"));
        tema5.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota5"));
        tema6.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota6"));
        tema7.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota7"));
        tema8.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota8"));
        tema9.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota9"));
        tema10.setCellValueFactory(new PropertyValueFactory<NotaDTOStudent,Float>("nota10"));


        this.list.setAll(list);
        tableViewGrupaFilter.setItems(this.list);
    }

}
