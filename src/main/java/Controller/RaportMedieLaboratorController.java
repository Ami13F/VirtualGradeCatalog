package Controller;

import Utils.GeneratePDF;
import Utils.Observer.Observer;
import common.Rapoarte;
import common.Service.ServiceNota;
import common.Service.ServiceStudent;
import common.Service.ServiceTema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import main.MainGui;

import java.io.IOException;
import java.util.Map;

public class RaportMedieLaboratorController implements Observer {

    private Stage stage;

    private ObservableList<PieChart.Data> pieChartPromovabilityList = FXCollections.observableArrayList();

    private Rapoarte raportService;

    private Map<?, ?> exportType;
    private String fileNameForExport = "RaportMedieStudentiLaborator";

    @FXML Tab raportMedieLaboratorTab;
    @FXML Tab raportMediePerLaboratorTab;
    @FXML Tab raportPromovabilitateTab;
    @FXML Button exportBtn;


    @FXML PieChart pieChartPromovability;
    @FXML LineChart<String, Float> lineChartMedieLaborator;
    @FXML BarChart<String, Integer> barChartMedieStudentLaborator;



    void setStage(Stage stage, ServiceStudent serviceStudent, ServiceTema serviceTema, ServiceNota serviceNota) {
        this.stage = stage;
        raportService = new Rapoarte(serviceStudent,serviceTema,serviceNota);
        exportType = raportService.medieNoteStudent();
        initModel();
        pieChart();
    }

    private void initModel(){
        raportMedieLaboratorTab.selectedProperty()
              .addListener(x->raportMedieLaboratorTabMethod());
        raportMediePerLaboratorTab.selectedProperty()
                .addListener(x->raportMedieLaboratorTabMethod());
        raportPromovabilitateTab.selectedProperty()
                .addListener(x->raportMedieLaboratorTabMethod());
    }

    /**
     * Alege tipul de fisier care trebuie exportat in format pdf.
     */
    private void raportMedieLaboratorTabMethod(){
        if(raportMedieLaboratorTab.isSelected()){
            exportType = raportService.medieNoteStudent();
            fileNameForExport = "RaportMedieStudentiLaborator";
        }else if(raportMediePerLaboratorTab.isSelected() ){
            exportType = raportService.mediaNotelorPerTemaSortat();
            fileNameForExport = "RaportMedieTemeLaborator";
        }else {
            exportType = raportService.studentiExamen();
            fileNameForExport = "StudentiExamen";
        }
    }

    /**
     * Export when export Button is on
     */
    @FXML
    public void handleExport(){
        if(!fileNameForExport.equals("")) {

            GeneratePDF.generatePDF(fileNameForExport, exportType);
        }
    }

    /**
     * Apel pt toate chart-urile
     */
    private void pieChart() {
        pieChartPromovability();
        lineChartMedieLaborator();
        barChartMedieStudentLaborator();
    }

    /**
     * Pie chart care indica promovabilitatea studentilor
     * Promovati sau respinsi.
     */
    private void pieChartPromovability(){
        long nrPromovat  = raportService.studentiExamen().values()
                .stream()
                .filter(e->e.contains("Promovat"))
                .count();
        long nrPicati  = raportService.studentiExamen().values()
                .stream()
                .filter(e->e.contains("Respins"))
                .count();

        pieChartPromovabilityList.add(new PieChart.Data("Promovat",nrPromovat));
        pieChartPromovabilityList.add(new PieChart.Data("Respins",nrPicati));

        pieChartPromovability.setData(pieChartPromovabilityList);


    }

    /**
     * Line chart pentru notele de la laborator.
     */
    private void lineChartMedieLaborator(){
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Numar laborator");
        yAxis.setLabel("Media");

        XYChart.Series<String, Float> series = new XYChart.Series<>();

        series.setName("Medie note");
        //populating the series with data
        raportService.mediaNotelorPerTema().forEach((k,v)->
            series.getData().add(new XYChart.Data<>(k, v)));


        lineChartMedieLaborator.getData().add(series);

    }

    /**
     * Bar chart
     */
    private void barChartMedieStudentLaborator(){

        XYChart.Series<String,Integer> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("NrStudenti");

        int[] numValues = new int[5];
        raportService.medieNoteStudent().forEach((k,v)->{
            int index = Math.min(4, v.intValue()/2);
           numValues[index]+=1;
        });
        int start = 0;

        int end = 2;

        for(int i = 0;i<5;i++){
            dataSeries1.getData().add(new XYChart.Data<>( start+"-"+end,numValues[i]));
            end+=2;
            start+=2;
        }

        barChartMedieStudentLaborator.getData().add(dataSeries1);

    }


    /**
     * Deschide fereastra de log in
     * @throws IOException Daca View nu exista
     */
    @FXML
    public void handleBackButton()throws IOException {
        String startView = MainGui.startView;
        BackButtonClass.backButtonMethod(startView,stage);
    }


    @Override
    public void update() {

    }
}
