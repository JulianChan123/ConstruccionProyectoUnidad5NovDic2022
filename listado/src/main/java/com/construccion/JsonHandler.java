package com.construccion;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {
    public ArrayList<Employee> getObjects(String jsonInfo) throws JsonMappingException, JsonProcessingException{

        ArrayList<JSONObject> objectList = getInfoToArray(jsonInfo);
        ArrayList<Employee> employeeList = new ArrayList<>();
        for(int i = 0; i < objectList.size(); i++){
            org.json.JSONObject jsonObject = objectList.get(i);
            ObjectMapper mapper = new ObjectMapper();
            Employee employee = mapper.readValue(jsonObject.toString(), Employee.class);
            employeeList.add(employee);
        }
        return employeeList;
    }

    private ArrayList<JSONObject> getInfoToArray(String jsonInfo){
        JSONObject jsonObject = new JSONObject(jsonInfo);
        JSONArray jsonArray = jsonObject.getJSONArray("employee");
        ArrayList<JSONObject> employeeObjects = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            employeeObjects.add(object);
        }
        return employeeObjects;
    }

    public void modifyJsonFile(ArrayList<Employee> employeeList){
        try {
            PrintWriter out = new PrintWriter(new FileWriter("./jsonFile/employee.json"));
            out.write(getJsonContent(employeeList).toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJsonContent(ArrayList<Employee> employeeList){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonFile = new JSONObject();
        for(int i = 0; i < employeeList.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", employeeList.get(i).getId());
            jsonObject.put("firstName", employeeList.get(i).getFirstName());
            jsonObject.put("lastName", employeeList.get(i).getLastName());
            jsonObject.put("photo", employeeList.get(i).getPhoto());
            jsonArray.put(jsonObject);
        }
        jsonFile.put("employee",jsonArray);
        return jsonFile;
    }
}
