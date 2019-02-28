package Controller;

import common.Service.ServiceStudent;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ComboBoxClass {

    public static void initComboBoxAutoComplete(ServiceStudent serviceStudent, ComboBox<String> nume){
        List<String> lista = StreamSupport
                .stream(serviceStudent.findAllStudent().spliterator(), false)
                .map(m->m.getNumele() + "(" + m.getID() + ")")
                .collect(Collectors.toList());
        nume.getItems().addAll(lista);
        new AutoCompleteComboBoxListener<>(nume);
    }


    public static void initComboBoxGrupa(ComboBox grupaComboBox) {
        List<Integer> grupa = new ArrayList<>();
        grupa.add(221);
        grupa.add(222);
        grupa.add(223);
        grupa.add(224);
        grupa.add(225);
        grupa.add(226);
        grupa.add(227);
        List<String> list = StreamSupport.stream(grupa.spliterator(), false)
                .map(Object::toString)
                .collect(Collectors.toList());
        grupaComboBox.getItems().addAll(list);
        //new AutoCompleteComboBoxListener<String>(grupaComboBox);
    }

    public static void initComboBoxTema(ComboBox temaComboBox) {
        List<String> tema = new ArrayList<>();
        tema.add("Lab1");
        tema.add("Lab2");
        tema.add("Lab3");
        tema.add("Lab4");
        tema.add("Lab5");
        tema.add("Lab6");
        tema.add("Lab7");
        tema.add("Lab8");
        tema.add("Lab9");
        tema.add("Lab10");
        tema.add("Lab11");
        tema.add("Lab12");
        tema.add("Lab13");
        List<String> list = StreamSupport.stream(tema.spliterator(), false)
                .map(m -> m)
                .collect(Collectors.toList());
        temaComboBox.getItems().addAll(list);

    }

    public static void initComboBoxProf(ComboBox numeProfComboBox) {
        List<String> prof= new ArrayList<>();
        prof.add("Camelia");
        prof.add("Adriana");
        List<String> list= prof.stream()
                .map(m->m)
                .collect(Collectors.toList());
        numeProfComboBox.getItems().addAll(list);

    }

    public static void initDataPredareComboBox(ComboBox dataPredareComboBox) {
        List<Integer> dataPredare = Arrays.asList(1, 2, 3, 4, 5,6,7,8,9,10,11,12,13,14);

        List<Integer> list = dataPredare.stream()
                .map(m -> m)
                .collect(Collectors.toList());
        dataPredareComboBox.getItems().addAll(list);

    }
}
