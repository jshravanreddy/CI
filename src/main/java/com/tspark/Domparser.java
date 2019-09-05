package com.tspark;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Domparser {

    public static void main(String[] args) {
        String xmlFile = "D:\\Integration\\Impro-English.xml";
        File file = new File(xmlFile);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        System.out.println("Root : " + doc.getDocumentElement().getNodeName());
        System.out.println("\n names in the xml: ");
        NodeList nameList = doc.getElementsByTagName("name");
        for (int i = 0; i < nameList.getLength(); i++) {
            Element el = (Element) nameList.item(i);
            System.out.println(el.getNodeName() + " : " + el.getTextContent());
        }

    }

}