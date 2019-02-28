package common.Repository.RepositoryXML;

import ValidatorPackage.Validator;
import common.Domain.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StudentRepositotyXML extends AbstractXMLRepository<Integer, Student> {
    public StudentRepositotyXML(Validator<Student> val, String fileName) {
        super(val, fileName);
    }

    @Override
    protected Student createEntityFromElement(Element el) {
        Student s = new Student();
        Integer sId = Integer.parseInt(el.getElementsByTagName("id").item(0).getTextContent());
        String sNume= el.getElementsByTagName("nume").item(0).getTextContent();
        Integer sGrupa = Integer.parseInt(el.getElementsByTagName("grupa").item(0).getTextContent());
        String sEmail = el.getElementsByTagName("email").item(0).getTextContent();
        String sNProf= el.getElementsByTagName("numeProfesor").item(0).getTextContent();
        s.setID(sId);
        s.setNumeProfesor(sNProf);
        s.setEmail(sEmail);
        s.setNumele(sNume);
        s.setGrupa(sGrupa);

        return s;
    }

    @Override
    protected Element createElementFromEntity(Document document, Student entity) {
        Element element = document.createElement("Student");

        Element id = document.createElement("id");
        id.setTextContent(entity.getID().toString());
        element.appendChild(id);

        Element nume = document.createElement("nume");
        nume.setTextContent(entity.getNumele());
        element.appendChild(nume);

        Element grupa = document.createElement("grupa");
        grupa.setTextContent(entity.getGrupa().toString());
        element.appendChild(grupa);

        Element email = document.createElement("email");
        email.setTextContent(entity.getEmail());
        element.appendChild(email);

        Element numeP = document.createElement("numeProfesor");
        numeP.setTextContent(entity.getNumeProfesor());
        element.appendChild(numeP);


        return element;
    }

    @Override
    public Student extractEntity(String entity) {
        return null;
    }
}
