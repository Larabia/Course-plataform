package com.ada.cursos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UsuarioTest {

	@Test
	public void altaUsuario() throws UnirestException {
	    HttpResponse<JsonNode> jsonResponse 
	      = Unirest.post("http://localhost:8080/usuarioController/alta")
	      .header("accept", "application/json").queryString("test2@mock.com", "test222")
	      .asJson();
	 
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(200, jsonResponse.getStatus());
	}
}
