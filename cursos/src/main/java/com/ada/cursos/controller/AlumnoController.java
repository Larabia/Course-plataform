package com.ada.cursos.controller;

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

import com.ada.cursos.form.AlumnoForm;
import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.Curso;
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

	@GetMapping(path = "/{id}")
	@Operation(summary = "alumnoPorId", description = "Recibe como un Long id y devuelve el alumno con el id correspondiente.")
	public ResponseEntity<Alumno> alumnoPorId(@PathVariable Long id) {

		log.info("Metodo alumnoPorId: buscando alumno id"+id);
		Alumno alumno = alumnoServ.porId(id);		
		
		log.info("Alumno encontrado");
		return new ResponseEntity<>(alumno, HttpStatus.OK);
	}

	@PostMapping(path = "/alta")
	@Operation (summary = "Alta Alumno", description = "Ingrasa un objeto alumno con datosSE a la base de datos")
	
	public ResponseEntity<Alumno> altaAlumno(@RequestBody AlumnoForm alumnoForm) {	
				
		log.info("metodo: altaAlumno.");
		
		Alumno alumno = new Alumno();
		alumno = alumnoServ.cargarDatosForm(alumnoForm, alumno);				
		alumnoRepo.save(alumno);
		
		log.info("Alumno guardado.");

		return new ResponseEntity<>(alumno, HttpStatus.CREATED);

	}
	

}