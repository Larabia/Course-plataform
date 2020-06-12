package com.ada.cursos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CursoTest {
	
	@Test
	public void altaCurso() throws UnirestException {
		
	    Map<String, Object> fields = new HashMap<>();
	    fields.put("id", "2");
	    fields.put("email", "test2@mock.com");
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080/cursoController/alta")
	      .header("accept", "application/json").body("{\"nombre\":\"cursoTEST2\","
		      		+ " \"horas\":\"5\"}")
		      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}

}
