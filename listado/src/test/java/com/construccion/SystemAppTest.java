package com.construccion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

class SystemAppTest {
	
	SystemApp systemApp;

	@BeforeEach
	void setUp() throws Exception {
		systemApp = new SystemApp("../jsonFile/", "employee.json");
	}

	@Test
	@DisplayName("Probando si verifica existencia")
	void test() {
		
		assertTrue(systemApp.verifyFileExistence());
	}
	
	
	@Test
	@DisplayName("Probando si es un archivo valido")
	void shouldIsValidFile() {
		assertTrue(systemApp.isValidFile());
	}

	@Test
	@DisplayName("Probando si busca")
	void shouldSearch() throws JsonMappingException, JsonProcessingException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees = systemApp.getEmployeeObjects();
		int indexObject = systemApp.searchEmployeeIndex(employees, "1");
		

		assertEquals(employees.get(indexObject).getFirstName(), employees.get(0).getFirstName());

	}
	
}