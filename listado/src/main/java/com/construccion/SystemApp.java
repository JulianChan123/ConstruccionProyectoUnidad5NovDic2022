package com.construccion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class SystemApp {
    private String path;
    private String fileName;

    public SystemApp(String path, String fileName){
        this.path = path;
        this.fileName = fileName;
    }

    public boolean verifyFileExistence(){
    	File file = new File(path, fileName);
        return file.exists();
    }

    public String getJsonText(){
        File file = new File(path, fileName);
        String jsonText = "";
        Scanner lector;

        try{
            lector = new Scanner(new BufferedReader(new FileReader(file)));
            while(lector.hasNextLine()){
                jsonText += lector.nextLine() + "\n";
            }
            lector.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return jsonText;
    }

    private boolean verifyJsonFormat(){
        String jsonText = getJsonText();
        boolean valid = false;;
        try {
            new JSONObject(jsonText);
        } catch (JSONException e) {
            e.printStackTrace();
            return valid;
        }
        valid = true;
        return valid;
    }

    public boolean isValidFile(){
        boolean valid = false;
        if(verifyJsonFormat() & verifyFileExistence()){
            return !valid;
        }else{
            return valid;
        }
    }

    public ArrayList<Employee> getEmployeeObjects() throws JsonMappingException, JsonProcessingException{
        JsonHandler jsonHandler = new JsonHandler();
        return jsonHandler.getObjects(getJsonText());
    }

    public void modifyJsonFile(ArrayList<Employee> employeeList) throws JsonMappingException, JsonProcessingException{
        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.modifyJsonFile(employeeList);
    }

    public int searchEmployeeIndex(ArrayList<Employee> employeeList, String id){

        for(Employee employee: employeeList){
            if(employee.getId().equals(id)){
                return employeeList.indexOf(employee);
            }
        }
        return employeeList.size()+1;
    }
}
