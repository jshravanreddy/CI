package com.tspark;

import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadingXMLTest {

    @org.junit.jupiter.api.Test
    void convertXMLToRSJson() {
        ReadingXML readinXML=new ReadingXML();
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonarray = new JSONArray();
        // reading actual file then compare this with expected
        try (BufferedReader reader = new BufferedReader(new FileReader("D://integration//json")))
        {
            Object obj =  jsonParser.parse(reader);
            jsonarray.add(obj);
            System.out.println(jsonarray);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Assertions.assertEquals(jsonarray, readinXML.convertXMLStringToJson2(readinXML.convertXMLToXMLString("D:/integration/Impro-English - Copy.xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}