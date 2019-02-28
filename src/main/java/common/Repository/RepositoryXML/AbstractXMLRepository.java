package common.Repository.RepositoryXML;

import ValidatorPackage.Validator;
import common.Domain.HasID;
import common.Repository.AbstractFileRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public abstract class AbstractXMLRepository<ID, E extends HasID<ID>> extends AbstractFileRepository<ID, E> {


    public AbstractXMLRepository(Validator<E> val, String fileName){
        super(fileName, val);

    }

    protected abstract E createEntityFromElement(Element element);
    protected abstract Element createElementFromEntity(Document document,E entity);

    @Override
    public void loadFromFile(){
        try{
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(super.fileName);

            Element root = document.getDocumentElement();
            NodeList nodeList = ((Element) root).getChildNodes();
            for(int i = 0;i<nodeList.getLength();i++){
                Node nodE = nodeList.item(i);
                if(nodE.getNodeType() == Node.ELEMENT_NODE){
                    E e = createEntityFromElement((Element)nodE);
                    super.save(e);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void reload(){
        try{
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root = document.createElement("Lista");
            document.appendChild(root);
            findAll().forEach(entity -> {
                Element studElem = createElementFromEntity(document, entity);
                root.appendChild(studElem);
            });

            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTransformer();
            transformer.transform(new DOMSource(document),new StreamResult(super.fileName));

        }catch(Exception e){
            e.printStackTrace();
        }

    }




}
