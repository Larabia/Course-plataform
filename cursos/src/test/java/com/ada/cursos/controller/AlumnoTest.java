package com.ada.cursos.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AlumnoTest {
	
	@Test
	public void altaAlumno() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080/alumnoController/alta")
	      .header("accept", "application/json").body("{\"id\":\"2\","
	      		+ " \"nombre\":\"mock2\","
	      		+ " \"apellido\":\"mock22\","
	      		+ " \"estudia\":\"true\","
	      		+ " \"trabaja\":\"false\"}")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}

}
