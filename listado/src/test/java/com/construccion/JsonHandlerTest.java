package com.construccion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JsonHandlerTest {
    JsonHandler jHandler;
    SystemApp systemApp;

	@BeforeEach
	void setUp() throws Exception {
		systemApp = new SystemApp("../jsonFile/", "employee.json");
        
	}

	@Test
	@DisplayName("Probando si los datos JSON se convierten a objetos")
	void shouldGetObjects() throws JsonMappingException, JsonProcessingException  {
        ArrayList<Employee> employees = new ArrayList<Employee>();
		employees = systemApp.getEmployeeObjects();
        assertNotNull(employees);
	}

	@Test
	@DisplayName("Probando si los datos JSON se modifican")
	void shouldModify() throws JsonMappingException, JsonProcessingException  {
        ArrayList<Employee> employees = new ArrayList<Employee>();
		employees = systemApp.getEmployeeObjects();
		Employee employee = employees.get(1);
		employee.setFirstName("Texto prueba");
		employees.set(1, employee);
		systemApp.modifyJsonFile(employees);
		employees = systemApp.getEmployeeObjects();
		Employee employee2 = employees.get(1);
		assertNotEquals(employee.getFirstName(),employee2.getFirstName());
	}

	@Test
	@DisplayName("Probando si se agregan datos al JSON")
	void shouldAdd() throws JsonMappingException, JsonProcessingException  {
        ArrayList<Employee> employees = new ArrayList<Employee>();
		employees = systemApp.getEmployeeObjects();
		int firtSize = employees.size();
		Employee employee = new Employee();
		employee.setId("4");
		employee.setFirstName("asdas");
		employee.setLastName("asdas");
		employee.setPhoto("https://jsonformatter.org/img/Robert-Downey-Jr.jpg");
		employees.add(employee);
		systemApp.modifyJsonFile(employees);
		int secondSize = employees.size();
		assertEquals(firtSize, secondSize);
	}

}
