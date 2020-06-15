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
import com.ada.cursos.model.DatosSE;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/alumnoController")
public class AlumnoController {
	
	@Autowired
	private AlumnoRepository alumnoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	Logger log = Logger.getLogger(AlumnoRepository.class.getName());

	// POST

	@PostMapping(path = "/alta")
	@Operation (summary = "Alta Alumno", description = "Ingrasa un objeto alumno a la base de datos")
	
	public ResponseEntity<Alumno> altaAlumno(@RequestBody AlumnoForm alumnoForm) {	
		
		log.info("metodo: altaAlumno.");
		
		Alumno alumno = new Alumno();
		
		java.util.Optional<Usuario> usuario = usuarioRepo.findById((long) alumnoForm.getId());
		Usuario usuarioRespuesta = usuario.get();
		
		DatosSE datosSE = new DatosSE();
		datosSE.setEstudia(alumnoForm.isEstudia());
		datosSE.setTrabaja(alumnoForm.isTrabaja());
		
		alumno.setUsuario(usuarioRespuesta);		
		alumno.setNombre(alumnoForm.getNombre());
		alumno.setApellido(alumnoForm.getApellido());
		alumno.setDatosSE(datosSE);
		
		
		alumnoRepo.save(alumno);
		log.info("metodo: Alumno guardado.");

		return new ResponseEntity<>(alumno, HttpStatus.CREATED);

	}

}