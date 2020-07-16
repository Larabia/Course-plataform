package com.ada.cursos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EmpresaControllerTest {
	
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnb2QiLCJpYXQiOjE1OTQ4NTc3OTEsImV4cCI6MTU5NDg5Mzc5MX0.POlfl5ZG-Mt7_JrhLpZ3sVqivAySZBGNgaUw2DlgphtPI3XQQxF5ZXfO9P7nqaDS_YLerj2cU8PWqBOBD15DSA";
	private String empresaForm = "{\r\n" + 
			"\"id\":\"8\",  \r\n" + 
			"\"repId\":\"10\",\r\n" + 
			"\"nombre\":\"MODIFICADO2\",\r\n" + 
			"\"cuil\":\"2222\",\r\n" + 
			"\"tipo\":\"tec\",\r\n" + 
			"\"dir\":\"test222\",\r\n" + 
			"\"categoria\":\"test222\",\r\n" + 
			"\"añoFun\":\"2002\",\r\n" + 
			"\"tel\":\"2222222\"\r\n" + 
			"\r\n" + 
			"}";
	
	private String id = "12";
	
	@Test
	public void altaEmpresa() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080/empresa/")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(empresaForm)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(201, jsonResponse.getStatus());
	}
	
	@Test
	public void empresaPorId() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/empresa/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarEmpresas() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/empresa/")
	      .header("accept", "application/json").header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void aprobarEmpresa() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://localhost:8080/empresa/aprobar/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void modificarEmpresa() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://localhost:8080/empresa/")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(empresaForm)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void borrarEmpresa() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.delete("http://localhost:8080/empresa/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	

}
