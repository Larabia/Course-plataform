package com.ada.cursos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CursoControllerTest {

	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnb2QiLCJpYXQiOjE1OTU5NzkyMTcsImV4cCI6MTU5NjAxNTIxN30.pjAh4_apmfcuOn6zSxNEi54Zg28uNDblPINZKoWouZ6iJv5fhmyrCRF6-WYG6kmt1c6YP8hfC4m1VD81eayiPg";
	private String cursoForm = "{\n" + "\"empId\":\"1\",\n" + "\"nombre\":\"cursoTEST3\",\n" + "\"horas\": \"10\",\n"
			+ "\"modalidad\":\"cursoTEST3\",\n" + "\"precio\":\"2000\",\n" + "\"categoria\":\"cursoTEST13\",\n"
			+ "\"cupo\":\"2\",\n" + "\"cupoBecas\":\"1\",\n" + "\"abierto\":\"true\"\n" + "\n" + "}";
	private String cursoForm2 = "{\n" + 
			"\"id\":\"24\",\n" + 
			"\"empId\":\"1\",\n" + 
			"\"nombre\":\"modificado\",\n" + 
			"\"horas\": \"10\",\n" + 
			"\"modalidad\":\"modificado\",\n" + 
			"\"precio\":\"2000\",\n" + 
			"\"categoria\":\"cursoTEST22\",\n" + 
			"\"cupo\":\"100\",\n" + 
			"\"cupoBecas\":\"30\",\n" + 
			"\"abierto\":\"true\"\n" + 
			"\n" + 
			"}";
	private String id = "29";
	private Long empId = (long) 1;

	@Test
	public void altaCurso() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/curso/")
				.header("accept", "application/json")
				.header("Authorization", "Bearer " + token)
				.header("Content-Type", "application/json")
				.body(cursoForm).asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(201, jsonResponse.getStatus());
	}
	
	@Test
	public void cursoPorId() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/curso/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .queryString("porcentBeca", "50%")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarCursos() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/curso/")
	      .header("accept", "application/json").header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarPorCategoria() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/curso/categoria")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .queryString("categoria", "cursoTEST1")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarPorEmpresa() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/curso/empresa")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .queryString("id", empId)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarPorEmpresaYcategoria() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/curso/empresa-y-categoria")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .queryString("id", empId)
	      .queryString("categoria", "cursoTEST1")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void modificarCurso() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://localhost:8080/curso/")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(cursoForm2)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void borrarCurso() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.delete("http://localhost:8080/curso/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}

}
