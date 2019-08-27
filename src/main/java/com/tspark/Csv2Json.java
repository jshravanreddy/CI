package com.tspark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
//import java.util.stream.Collectors;

public class Csv2Json  {

    public static void main(String[] s){


        BufferedReader br=null;


String csvfile="D:\\Integration\\csv";
        Pattern pattern=Pattern.compile(",");
        try{
            br=new BufferedReader(new FileReader(csvfile));
           List<Model> mdl=br.lines().skip(1).map(line->{
           String[] x= pattern.split(line);
        //   return new Model(x[0],x[1],x[2],Integer.parseInt(x[3]),x[4],x[5], Integer.parseInt(x[6]), Integer.parseInt(x[7]),x[8],Integer.parseInt(x[9]),
          //         Integer.parseInt(x[10]));
               return new Model(x[0],x[1],x[2],x[3],x[4],x[5],x[6],x[7],x[8],x[9],
                       x[10]);
           }).collect(Collectors.toList());
            System.out.println(mdl);
            ObjectMapper mapper=new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(System.out, mdl);
           // Gson g = new Gson();
           // Model p = g.fromJson(mdl, Model.class);
        }


        catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
