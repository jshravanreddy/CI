package com.tspark;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.json.XML;

import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ReadingXML {

    //TO Store all the Entites
    JsonObject productData = new JsonObject();
    //To store all the attributes of  each entity
    JsonObject attributes = new JsonObject();
    //To store each attribute of Entity
    JsonObject attribute = new JsonObject();
    // To store  Values array of Attribute
    JsonArray values = new JsonArray();
    //To Store  properties of  a attribute's value
    JsonObject value = new JsonObject();
    //To Store vlaues json object of a attribute
    JsonObject productName = new JsonObject();
    //TO Store each entity to entity object
    JsonArray entities =  new JsonArray();
    //To store all the entities to Entity JSON OBJECT
    JsonObject entity = new JsonObject();
    //Creating GSON Object
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

    public ReadingXML() { }

    public static void main(String args[]) throws IOException {
        ReadingXML rsxml = new ReadingXML();
        Document doc= rsxml.convertXMLToDOM();
        rsxml.convertXMLToRSJson(doc);
        // String xmlString=  rsxml.convertDOMToString(doc);
        String xmlString=rsxml.convertXMLToXMLString("D:/integration/Impro-English - Copy.xml");
        rsxml.convertXMLStringToJson(xmlString);//manually
        rsxml.convertXMLStringToJson2(xmlString);// usimg org.json.XML

    }

    public void convertXMLStringToJson(String xmlString){

        // split by start tag, value and end tag
     String[] splits= xmlString.split("\\s+(?=<)|(?<=>)|(?=</)|(?=>)\\s+");
     List<String> lst= new ArrayList<>();
     lst.addAll(Arrays.asList(splits));
     lst.remove(0);
     JsonObject jsonobject=new JsonObject();
     JsonArray jsonarray=new JsonArray();
     String key=null;
     String root=splits[1];
     System.out.println("ROOT IS ----"+root);

      for (String line:splits) {
          Pattern pattern = Pattern.compile("<(.*?)>");
          Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            key=matcher.group(1);
           // System.out.println("start tag  "+key);
            continue;
          }
          else{
              //System.out.println(line);
          jsonobject.addProperty(key, line);
          }
      }
        System.out.println("JSONOBJ  "+jsonobject);
       // final Map<String, Object> holder = new HashMap<>();
       // holder.put(root, jsonobject);
}


    public String convertXMLToXMLString(String path) throws IOException{
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
           String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
        }
        //String xml = Files.lines(Paths.getPath(path)).collect(Collectors.joining("\n"));
        System.out.println("---------------------------------xml file to xmlstring  ------------------------------------------ ");
        System.out.println(sb.toString());
        return sb.toString();
    }

public JSONObject convertXMLStringToJson2(String xmlString){
    JSONObject jsonObj = XML.toJSONObject(xmlString);
    System.out.println("jsonObj------------"+jsonObj.toString());
    return jsonObj;
}


    public String convertDOMToString(Document doc){
        StringWriter writer=null;
        try
        {
            DOMSource domSource = new DOMSource(doc);
            writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            //  return writer.toString();
            System.out.println("---------------------------------DOM to XMLString ------------------------------------------ ");
            System.out.println(writer.toString());
        }
        catch(TransformerException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return writer.toString();
    }
    public  Document convertXMLToDOM(){
        Document doc=null;
        DocumentBuilder dBuilder;
        try {
            File fXmlFile = new File("D:/integration/Impro-English - Copy.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setIgnoringElementContentWhitespace(true);
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public  JsonArray convertXMLToRSJson(Document doc) {


        //Reaading All the PRODUCT Elements from XML
        // Document doc=readXml();

        NodeList productList = doc.getElementsByTagName("product");


        int i = 0;
        String attributeName = "";
        String textValue = "";
        for (i = 0; i <productList.getLength(); i++) {
            Node node = productList.item(i);
            NodeList productAttributes;
            productAttributes = node.getChildNodes();
            // System.out.println("Product Attributes.." + productAttributes.getLength());
            for (int j = 0; j < productAttributes.getLength(); j++) {
                Node attrubte = productAttributes.item(j);
                //To add  propretires to value array
                value.addProperty("source", "internal");
                value.addProperty("locale", "en-us");
                //To check element type
                if (attrubte.getNodeType() == Node.ELEMENT_NODE) {
                    attributeName = attrubte.getNodeName();
                    textValue = attrubte.getTextContent();
                        /*
                            Adding attribute value to  value property
                         */
                    value.addProperty("value", textValue);
                    //Adding value Json object to Values array
                    values.add(value);
                    //Preparing Values JSON Object
                    productName.add("values", values);
                    //System.out.println("Product  " + productName);
                    //Preparing Attrubte JSON Object
                    attribute.add(attributeName, productName);
                    // System.out.println("Attribute " + attribute);

                    //Adding Each Attribute JSON Object to List of type JSON Object.
                    // attributesList.add(attribute);
                    //Making  Empty to avoid Duplicates adding to JSON Objects and arrays
                    value = new JsonObject();
                    values = new JsonArray();
                    productName = new JsonObject();
                }
            }

            /**
             * Adding all  the attributes of one entity to attributes JSON Object
             * Need to Work around not yet completed.
             */
            //attributes.add("attributes",test);

            //System.out.println("----attributes final----"+gson.toJson(attributes));
            /**
             * Final output for one  entity .
             * Need to work around more for more than one entity
             */
            //System.out.println("--- test Json--- " + gson.toJson(attributesList));
            // System.out.println("Attribute " + attribute);
            attributes.add("attributes",attribute);
            JsonObject properties = new JsonObject();
            properties.addProperty("CreatedService","entityManagementService");
            properties.addProperty("CreatedBy","ramesh");
            productData.addProperty("id",1234);
            productData.addProperty("name","testRSJSON");
            productData.add("properties",properties);
            productData.add("data", attributes);
            //  System.out.println("Products " + productData);
            entities.add(productData);
            // System.out.println("..." + i +" Enitity " + entities);
        }
        System.out.println("Entites "+entities);
        System.out.println("Entites "+entities.size());
        entity.add("entties",entities);
        System.out.println(" Final Entites " + entity);
        return entities;

    }
}