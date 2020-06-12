package com.ada.cursos.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.form.RepForm;
import com.ada.cursos.model.Rep;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.repository.RepRepository;
import com.ada.cursos.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "/rep")
public class RepController {

	@Autowired
	private RepRepository repRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	Logger log = Logger.getLogger(AlumnoRepository.class.getName());

	// POST

	@PostMapping(path = "/alta")
	@Operation (summary = "Alta Rep", description = "Ingrasa un objeto Rep a la base de datos")
	
	public ResponseEntity<Rep> altaRep(@RequestBody RepForm repForm) {	
		
		log.info("metodo: altaRep.");
		
		Rep rep = new Rep();
		
		java.util.Optional<Usuario> usuarioOp = usuarioRepo.findById(repForm.getId());
		Usuario usuario = usuarioOp.get();
		
		rep.setUsuario(usuario);		
		rep.setNombre(repForm.getNombre());
		rep.setApellido(repForm.getApellido());	
		
		repRepo.save(rep);
		log.info("metodo: rep guardado.");

		return new ResponseEntity<>(rep, HttpStatus.CREATED);

	}
}
