package com.ada.cursos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AlumnoControllerTest {
	
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnb2QiLCJpYXQiOjE1OTQ4NDgxNTEsImV4cCI6MTU5NDg4NDE1MX0.LIWHdZPWMtu-adLZmAL1RV8VHHHqvYEEeWkYckzCDEkaHB0kiZAH2TvgkrFNPYqHcHm7be3jZkScuXnxyVUPeA";
	private String alumnoForm = "{\r\n" + 
			"\"id\":\"15\",\r\n" + 
			"\"nombre\":\"mock\",\r\n" + 
			"\"apellido\": \"mock1\",\r\n" + 
			"\"fechaNac\":\"1986-06-17\",\r\n" + 
			"\"genero\":\"femenino\",\r\n" + 
			"\"dir\":\"mock1111\",\r\n" + 
			"\"estudia\": \"true\",\r\n" + 
			"\"trabaja\": \"true\",\r\n" + 
			"\"ingresos\": \"true\",\r\n" + 
			"\"cantIngresos\": \"50000\",\r\n" + 
			"\"familia\": \"true\",\r\n" + 
			"\"cantFamiliares\": \"2\"\r\n" + 
			"}";
	
	private String mockId = "6";
	private String borrarId = "14";
	
	@Test
	public void alumnoPorId() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/alumno/{mockId}")
	      .routeParam("mockId", mockId)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarAlumnos() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/alumno/")
	      .header("accept", "application/json").header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarCursosEnProgreso() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/alumno/cursos-en-progreso")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .queryString("id", mockId)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarCursosFinalizados() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/alumno/cursos-finalizados")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .queryString("id", mockId)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void altaAlumno() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080/alumno/")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(alumnoForm)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(201, jsonResponse.getStatus());
	}
	
	@Test
	public void modificarAlumno() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://localhost:8080/alumno/")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(alumnoForm)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void borrarAlumno() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.delete("http://localhost:8080/alumno/{borrarId}")
	      .routeParam("borrarId", borrarId)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}


}
