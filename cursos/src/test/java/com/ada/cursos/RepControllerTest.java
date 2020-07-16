package com.ada.cursos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RepControllerTest {
	
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnb2QiLCJpYXQiOjE1OTQ4NDgxNTEsImV4cCI6MTU5NDg4NDE1MX0.LIWHdZPWMtu-adLZmAL1RV8VHHHqvYEEeWkYckzCDEkaHB0kiZAH2TvgkrFNPYqHcHm7be3jZkScuXnxyVUPeA";
	private String alumnoForm = "{\r\n" + 
			"\"id\":\"17\",\r\n" + 
			"\"nombre\":\"mock\",\r\n" + 
			"\"apellido\": \"mock22\",\r\n" + 
			"\"tipoDoc\": \"DNI\",\r\n" + 
			"\"doc\": \"2222222\",\r\n" + 
			"\"cargo\": \"test\"\r\n" + 
			"}";
	
	private String id = "17";
	
	
	@Test
	public void altaRep() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080/rep/")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(alumnoForm)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(201, jsonResponse.getStatus());
	}
	
	@Test
	public void repPorId() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/rep/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarReps() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/rep/")
	      .header("accept", "application/json").header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	
	@Test
	public void modificarRep() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://localhost:8080/rep/")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(alumnoForm)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void borrarRep() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.delete("http://localhost:8080/rep/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}

}
