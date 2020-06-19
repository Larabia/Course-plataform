package com.ada.cursos.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.form.LoginForm;
import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.UsuarioRepository;
import com.ada.cursos.service.UsuarioService;
import com.google.common.collect.Lists;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private UsuarioService usuarioServ;
	
    
	Logger log = Logger.getLogger(UsuarioRepository.class.getName());

	@GetMapping(path = "/{id}")
	@Operation(summary = "usuarioPorId", description = "Recibe como un Long id y devuelve el usuario con el id correspondiente.")
	public ResponseEntity<Usuario> usuarioPorId(@PathVariable Long id) {

		log.info("Metodo usuarioPorId: buscando usuario id"+id);
		Usuario usuario = usuarioServ.porId(id);		
		
		log.info("Usuario encontrado");
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listado")
	@Operation(summary = "listarUsuarios", description = "Lista todos los usuarios presentes en la base de datos.")
	public ResponseEntity<List<Usuario>> listarUsuarios() {

		log.info("Metodo listarUsuarios: listando usuario...");
		Iterable<Usuario> ListUsuarioIt = usuarioRepo.findAll();
		List<Usuario> listarUsuarios = Lists.newArrayList(ListUsuarioIt);

		log.info("Listado completo: listarUsuarios.");
		return new ResponseEntity<>(listarUsuarios, HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/alta")
	@Operation (summary = "Alta usuario", description = "Ingrasa un objeto usuario a la base de datos")
	
	public ResponseEntity<Usuario> altaUsuario(@RequestBody LoginForm loginForm) {
				
		log.info("metodo: altaUsuario.");
		
		Usuario usuario = usuarioServ.generarUsuarioDeForm(loginForm);
		usuarioRepo.save(usuario);
	
		log.info("metodo: Usuario guardado.");

		return new ResponseEntity<>(usuario, HttpStatus.CREATED);

	}
	
	
}

