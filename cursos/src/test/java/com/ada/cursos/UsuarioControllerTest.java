package com.ada.cursos;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UsuarioControllerTest {
	
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnb2QiLCJpYXQiOjE1OTQ4NDgxNTEsImV4cCI6MTU5NDg4NDE1MX0.LIWHdZPWMtu-adLZmAL1RV8VHHHqvYEEeWkYckzCDEkaHB0kiZAH2TvgkrFNPYqHcHm7be3jZkScuXnxyVUPeA";
	private String usuarioForm = "{\n" + 
			"\"id\": 7,\n" + 
			"\"nombreUsuario\": \"MODIFICADO\",\n" + 
			"\"email\":\"test@mock.com\",\n" + 
			"\"password\": \"MODIFICADO\"\n" + 
			"}";
	
	private String id = "7";
	
	
	@Test
	public void altaRep() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080//api/auth/nuevo")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(usuarioForm)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(201, jsonResponse.getStatus());
	}

	@Test
	public void usuarioPorId() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/usuario/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json").header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void listarUsuarios() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.get("http://localhost:8080/usuario/")
	      .header("accept", "application/json").header("Authorization", "Bearer " + token)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void modificarUsuario() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.put("http://localhost:8080/usuario/modificar")
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .body(usuarioForm)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void borrarUsuario() throws UnirestException {
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.delete("http://localhost:8080/usuario/{id}")
	      .routeParam("id", id)
	      .header("accept", "application/json")
	      .header("Authorization", "Bearer " + token)
	      .header("Content-Type", "application/json")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
}
