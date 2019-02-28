package common.Repository.RepositoryXML;

import ValidatorPackage.TemeValidator;
import common.Domain.Tema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TemaRepositoryXML extends AbstractXMLRepository<Integer, Tema> {


    public TemaRepositoryXML(TemeValidator val, String fileName) {
        super(val,fileName);
    }

    /**
     *
     * @param el Element
     * @return t Tema
     */
    @Override
    protected Tema createEntityFromElement(Element el) {
        Tema t = new Tema();
        Integer sId = Integer.parseInt(el.getElementsByTagName("id").item(0).getTextContent());
        String descriere= el.getElementsByTagName("descriere").item(0).getTextContent();
        Integer deadline = Integer.parseInt(el.getElementsByTagName("deadline").item(0).getTextContent());
        Integer dataPrimire = Integer.parseInt(el.getElementsByTagName("dataPrimire").item(0).getTextContent());

        t.setID(sId);
        t.setDescriere(descriere);
        t.setDeadline(deadline);
        t.setDataPrimire(dataPrimire);
        return t;
    }

    @Override
    protected Element createElementFromEntity(Document document, Tema entity) {
        Element element = document.createElement("Tema");

        Element id = document.createElement("id");
        id.setTextContent(entity.getID().toString());
        element.appendChild(id);

        Element nume = document.createElement("descriere");
        nume.setTextContent(entity.getDescriere());
        element.appendChild(nume);

        Element grupa = document.createElement("deadline");
        grupa.setTextContent(entity.getDeadline().toString());
        element.appendChild(grupa);

        Element email = document.createElement("dataPrimire");
        email.setTextContent(entity.getDataPrimire().toString());
        element.appendChild(email);


        return element;
    }

    @Override
    public Tema extractEntity(String entity) {
        return null;
    }
}
