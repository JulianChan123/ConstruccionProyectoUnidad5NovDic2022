package com.construccion;

/**
 * Hello world!
 *
 */
public class MostrarDatos 
{
    public static void main( String[] args )
    {
        SystemApp systemApp = new SystemApp("jsonFile/", "employee.json");
        System.out.println(systemApp.getJsonText());
    }
    
}
