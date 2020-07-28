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


	@Test
	public void cursoPorId() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/curso/{id}")
				.header("accept", "application/json").routeParam("id", "3").asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void listarCursos() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/curso/listado")
				.header("accept", "application/json").asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void listarAbiertos() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/curso/listado-abiertos")
				.header("accept", "application/json").asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void listarPorCategoria() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/curso/listado-por-categoria")
				.header("accept", "application/json")
				.queryString("categoria", "cursoTEST2")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void listarPorEmpresa() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/curso/listado-por-empresa")
				.header("accept", "application/json").queryString("id", "2").asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}



	@Test
	public void altaCurso() throws UnirestException {
	       		
	    Map<String, Object> fields = new HashMap<>();
	    fields.put("empId", "2");
	    fields.put("nombre", "cursoTEST4");
	    fields.put("horas", "10");
	    fields.put("modalidad", "online");
	    fields.put("precio", "2000");
	    fields.put("categoria", "cursoTEST1");
	    fields.put("cupo", "10");
	    fields.put("cupoBecas", "3");
	    fields.put("abierto", "true");
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080/curso/alta")
	      .header("Content-Type", "application/json").fields(fields)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
	
	
	@Test
	public void modificarCurso() throws UnirestException {
	       		
	    Map<String, Object> fields = new HashMap<>();
	    fields.put("empId", "2");
	    fields.put("nombre", "MODIFICADO");
	    fields.put("horas", "10");
	    fields.put("modalidad", "online");
	    fields.put("precio", "2000");
	    fields.put("categoria", "cursoTEST1");
	    fields.put("cupo", "10");
	    fields.put("cupoBecas", "3");
	    fields.put("abierto", "true");
	    
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080/curso/modificar/{id}")
	      .header("Content-Type", "application/json").routeParam("id", "3")
	      .fields(fields)
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void borrarCurso() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.delete("http://localhost:8080/curso/borrar/{id}")
				.header("accept", "application/json").routeParam("id", "7").asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

}
