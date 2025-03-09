package config;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Conf {

    String path;
    Document doc;

    public Conf(String path){
        try{
            this.path = path;
            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
            // Normalize the document
            doc.getDocumentElement().normalize();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getValueByKey(String key){
        return doc.getElementsByTagName(key).item(0).getTextContent();
    }
}
