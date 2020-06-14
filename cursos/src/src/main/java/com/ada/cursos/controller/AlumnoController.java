package com.ada.cursos.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.form.AlumnoForm;
import com.ada.cursos.model.Alumno;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.service.AlumnoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/alumno")
public class AlumnoController {
	
	@Autowired
	private AlumnoRepository alumnoRepo;
	
	@Autowired
	private AlumnoService alumnoServ;
	
	Logger log = Logger.getLogger(AlumnoRepository.class.getName());

	// POST

	@PostMapping(path = "/alta")
	@Operation (summary = "Alta Alumno", description = "Ingrasa un objeto alumno con datosSE a la base de datos")
	
	public ResponseEntity<Alumno> altaAlumno(@RequestBody AlumnoForm alumnoForm) {	
		
		log.info("metodo: altaAlumno.");
		
		Alumno alumno = alumnoServ.altaAlumno(alumnoForm);				
		alumnoRepo.save(alumno);
		
		log.info("metodo: Alumno guardado.");

		return new ResponseEntity<>(alumno, HttpStatus.CREATED);

	}
	

}