package com.ada.cursos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class InscripcionControllerTest {
	
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnb2QiLCJpYXQiOjE1OTU5NzkyMTcsImV4cCI6MTU5NjAxNTIxN30.pjAh4_apmfcuOn6zSxNEi54Zg28uNDblPINZKoWouZ6iJv5fhmyrCRF6-WYG6kmt1c6YP8hfC4m1VD81eayiPg";
	private String inscripcionForm = "{\"conBeca\":\"false\",\r\n" + 
			"\"alumnoId\":\"8\",\r\n" + 
			"\"cursoId\":\"2\"\r\n" + 
			"}";
	private String inscripcionForm2 = "{\"id\":\"15\",\r\n" + 
			"\"conBeca\":\"true\",\r\n" + 
			"\"becaAprobada\":\"true\",\r\n" + 
			"\"porcentBeca\":\"50\",\r\n" + 
			"\"alumnoId\":\"15\",\r\n" + 
			"\"cursoId\":\"2\",\r\n" + 
			"\"finalizado\":\"false\"\r\n" + 
			"}";
	
	private String id = "15";
	
	@Test
	public void altaInscripcion() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080/inscripcion/")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(inscripcionForm)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(201, jsonResponse.getStatus());
	}
	
	@Test
	public void inscripcionPorId() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/inscripcion/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .queryString("porcentBeca", "50%")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarInscripciones() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/inscripcion/")
	      .header("accept", "application/json").header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void modificarInscripcion() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://localhost:8080/inscripcion/")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(inscripcionForm2)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void aprobarBeca() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://localhost:8080//inscripcion/aprobar/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .queryString("porcentBeca", "50%")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void finalizarCursada() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://localhost:8080/inscripcion/cursada/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void borrarInscripcion() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.delete("http://localhost:8080/inscripcion/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	

}
