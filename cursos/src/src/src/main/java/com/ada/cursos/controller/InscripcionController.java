package com.ada.cursos.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ada.cursos.form.InscripcionForm;
import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.DatosSE;
import com.ada.cursos.model.Inscripcion;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.InscripcionRepository;
import com.ada.cursos.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping(path = "/inscripcionController")
public class InscripcionController {
	
	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	AlumnoRepository alumnoRepo;

	@Autowired
	CursoRepository cursoRepo;

	@Autowired
	InscripcionRepository inscripcionRepo;
	
	Log log = LogFactory.getLog(InscripcionController.class);
	
	// POST

		@PostMapping(path = "/alta")
		@Operation (summary = "Alta Inscripcion", description = "Ingrasa un objeto Inscripcion a la base de datos")
		
		public ResponseEntity<Inscripcion> altaInscripcion(@RequestBody InscripcionForm inscripcionForm) {	
			
			log.info("metodo: altaInscripcion.");
			
			Inscripcion inscripcion = new Inscripcion();
			
			java.util.Optional<Alumno> alumnoOp = alumnoRepo.findById(inscripcionForm.getAlumnoId());
			Alumno alumno = alumnoOp.get();
			
			java.util.Optional<Curso> cursoOp = cursoRepo.findById(inscripcionForm.getCursoId());
			Curso curso = cursoOp.get();
			
			inscripcion.setAlumno(alumno);
			inscripcion.setCurso(curso);
			inscripcion.setSolicitaBeca(inscripcionForm.isSolicitaBeca());
			
			inscripcionRepo.save(inscripcion);
			log.info("metodo: Inscripcion guardada.");

			return new ResponseEntity<>(inscripcion, HttpStatus.CREATED);

		}

}
