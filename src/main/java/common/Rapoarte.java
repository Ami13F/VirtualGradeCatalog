package common;

import com.google.common.collect.Lists;
import common.Domain.Nota;
import common.Domain.Student;
import common.Service.ServiceNota;
import common.Service.ServiceStudent;
import common.Service.ServiceTema;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Rapoarte {
    private ServiceNota serviceNota;
    private ServiceStudent serviceStudent;
    private ServiceTema serviceTema;

    public Rapoarte( ServiceStudent serviceStudent, ServiceTema serviceTema,ServiceNota serviceNota) {
        this.serviceNota = serviceNota;
        this.serviceStudent = serviceStudent;
        this.serviceTema = serviceTema;
    }
    /** <<<<<<<<<<<<<<<<<<<<<<<<<common.Rapoarte>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    /**
     * Media notelor unui student la laborator ->Nota la laborator pentru fiecare student
     * @return Map<Student,Float> studentul si media notelor lui
     */
    public Map<Student,Float> medieNoteStudent(){
        Map<Student,Float> mediaNote = new HashMap<>();

        //notele pt un student
        StreamSupport.stream(serviceStudent.findAllStudent().spliterator(),false)
                .forEach(stud->{
                     List<Nota> l = StreamSupport.stream(serviceNota.findAllNota().spliterator(),false)
                             .filter(n->{
                                 if(n.getStudent().getID().equals(stud.getID()))
                                         return true;
                                 else
                                     return false;
                                 })
                             .collect(Collectors.toList());
                    Double nota = l.stream().map(Nota::getNota).mapToDouble(Double::new).sum();
                    Integer nrLab = Lists.newArrayList(serviceTema.findAllTema()).size();
                    Float not = Float.parseFloat(nota.toString())/nrLab;
                    if(!l.isEmpty())
                        mediaNote.putIfAbsent(stud,not);
                });


        //sortarea
        Map sorted = mediaNote
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sorted;
    }
    /**
     * Media notelor la toate temele.
     * @return M cu descrierea laboratorului si media de la laborator
     */
    public Map<String,Float> mediaNotelorPerTemaSortat(){
        //media notelor la fiecare tema
        Map<String,List<Nota>> listaTeme = StreamSupport
                .stream(serviceNota.findAllNota().spliterator(),false)
                .collect(Collectors.groupingBy((Nota m) ->m.getTema().getDescriere()));
        Map<String,Float> mediaNote = new HashMap<>();
        listaTeme.entrySet().stream().forEach(k->{
            Integer nr = k.getValue().size();
            Float suma = k.getValue().stream()
                    .map(n->n.getNota())
                    .reduce(0f,(n1,n2)->n1+n2);
            mediaNote.put(k.getKey(),suma/nr);

        });

        Map<String,Float> sorted = mediaNote
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sorted;
    }
    public Map<String,Float> mediaNotelorPerTema(){
        Map<String,List<Nota>> listaTeme = StreamSupport
                .stream(serviceNota.findAllNota().spliterator(),false)
                .collect(Collectors.groupingBy((Nota m) ->m.getTema().getDescriere()));
        //media notelor la fiecare tema
        Map<String,Float> mediaNote = new HashMap<>();
        listaTeme.entrySet().stream().forEach(k->{
            Float suma = k.getValue().stream()
                    .map(n->n.getNota())
                    .reduce(0f,(n1,n2)->n1+n2);
            Integer nr = k.getValue().size();

            mediaNote.put(k.getKey(),suma/nr);
        });

        Map sorted = mediaNote
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        return sorted;
    }

    /**
     * Studenții care pot intra în examen (media mai mare sau egală cu 4).
     * @return Studentii care au media mai mare decat 4
     */
    public Map<Student,String> studentiExamen(){
        Map<Student,String> studentiCareTrec = new HashMap<>();
        Map<Student,Float> listaStudenti = medieNoteStudent();
        listaStudenti.forEach((stud,nota)->{
            if(nota>4)
                studentiCareTrec.putIfAbsent(stud, String.format("%.2f", nota).replace(',', '.') + " Promovat");
            else
                studentiCareTrec.putIfAbsent(stud, String.format("%.2f", nota).replace(',', '.') + " Respins");
        });
        return studentiCareTrec;
    }



    /**
     * Studenții care au predat la timp toate temele
     * @return
     */
    public List<Student> studentiCareAuPredatLaTimpToateTemele(){
        List<Student> listaStudenti = new ArrayList<>();


        return listaStudenti;
    }

}
