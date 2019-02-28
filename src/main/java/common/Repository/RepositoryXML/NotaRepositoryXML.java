package common.Repository.RepositoryXML;

import ValidatorPackage.Validator;
import common.Domain.Nota;
import common.Domain.Student;
import common.Domain.Tema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class NotaRepositoryXML extends AbstractXMLRepository<String, Nota> {
    public NotaRepositoryXML(Validator<Nota> val, String fileName) {
        super(val, fileName);
    }

    @Override
    protected Nota createEntityFromElement(Element el) {
        Nota n = new Nota();

        Student s = new Student();
        Integer sId = Integer.parseInt(el.getElementsByTagName("idStud").item(0).getTextContent());
        String sNume= el.getElementsByTagName("nume").item(0).getTextContent();
        Integer sGrupa = Integer.parseInt(el.getElementsByTagName("grupa").item(0).getTextContent());
        String sEmail = el.getElementsByTagName("email").item(0).getTextContent();
        String sNProf= el.getElementsByTagName("numeProfesor").item(0).getTextContent();
        s.setID(sId);
        s.setNumele(sNume);
        s.setGrupa(sGrupa);
        s.setEmail(sEmail);
        s.setNumeProfesor(sNProf);

        Tema t = new Tema();
        Integer tId = Integer.parseInt(el.getElementsByTagName("idTema").item(0).getTextContent());
        String descriere = el.getElementsByTagName("descriere").item(0).getTextContent();
        Integer deadline = Integer.parseInt(el.getElementsByTagName("deadline").item(0).getTextContent());
        Integer dataPrimire = Integer.parseInt(el.getElementsByTagName("dataPrimire").item(0).getTextContent());

        t.setID(tId);
        t.setDescriere(descriere);
        t.setDeadline(deadline);
        t.setDataPrimire(dataPrimire);


        Float nota = Float.parseFloat(el.getElementsByTagName("nota").item(0).getTextContent());

        Integer dataPredare = Integer.parseInt(el.getElementsByTagName("dataPredareTema").item(0).getTextContent());

        Nota not = new Nota(t,s,nota,dataPredare);
        not.setNota(nota);
        return not;

    }

    @Override
    protected Element createElementFromEntity(Document document, Nota entity) {
        Element element = document.createElement("Nota");

        Element id = document.createElement("idStud");
        id.setTextContent(entity.getStudent().getID().toString());
        element.appendChild(id);

        Element nume = document.createElement("nume");
        nume.setTextContent(entity.getStudent().getNumele());
        element.appendChild(nume);

        Element grupa = document.createElement("grupa");
        grupa.setTextContent(entity.getStudent().getGrupa().toString());
        element.appendChild(grupa);

        Element email = document.createElement("email");
        email.setTextContent(entity.getStudent().getEmail());
        element.appendChild(email);

        Element numeP = document.createElement("numeProfesor");
        numeP.setTextContent(entity.getStudent().getNumeProfesor());
        element.appendChild(numeP);


        Element idT = document.createElement("idTema");
        idT.setTextContent(entity.getTema().getID().toString());
        element.appendChild(idT);

        Element descriere = document.createElement("descriere");
        descriere.setTextContent(entity.getTema().getDescriere());
        element.appendChild(descriere);

        Element deadline = document.createElement("deadline");
        deadline.setTextContent(entity.getTema().getDeadline().toString());
        element.appendChild(deadline);

        Element dataPrimire = document.createElement("dataPrimire");
        dataPrimire.setTextContent(entity.getTema().getDataPrimire().toString());
        element.appendChild(dataPrimire);

        Element nota = document.createElement("nota");
        nota.setTextContent(entity.getNota().toString());
        element.appendChild(nota);

        Element dataPredareTema = document.createElement("dataPredareTema");
        dataPredareTema.setTextContent(entity.getDataPredareTema().toString());
        element.appendChild(dataPredareTema);

        return element;
    }

    @Override
    public Nota extractEntity(String entity) {
        return null;
    }
}
