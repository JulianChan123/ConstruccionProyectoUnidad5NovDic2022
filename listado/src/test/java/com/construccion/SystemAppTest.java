package com.construccion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
	@DisplayName("Probando si obtiene el texto del archivo")
	void shouldGetJSONText() {
		systemApp.getJsonText();
	}
	
	
	@Test
	@DisplayName("Probando si es un archivo valido")
	void shouldIsValidFile() {
		assertTrue(systemApp.isValidFile());
	}
	
}