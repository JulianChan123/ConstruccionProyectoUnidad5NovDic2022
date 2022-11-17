package com.construccion;

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
}
