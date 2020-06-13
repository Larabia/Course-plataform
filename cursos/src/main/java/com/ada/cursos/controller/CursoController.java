package com.ada.cursos.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.form.CursoForm;
import com.ada.cursos.model.Curso;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.EmpresaRepository;
import com.ada.cursos.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/curso")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepo;
	
	@Autowired
	private CursoService cursoServ;

	Logger log = Logger.getLogger(CursoRepository.class.getName());

	// POST

	@PostMapping(path = "/alta")
	@Operation(summary = "Alta curso", description = "Ingrasa un objeto curso a la base de datos")

	public ResponseEntity<Curso> altaCurso(@RequestBody CursoForm cursoForm) {

		log.info("metodo: altaCurso.");
		
		Curso curso = cursoServ.generarCursoDeForm(cursoForm);
		cursoRepo.save(curso);;

		log.info("metodo: Curso guardado.");

		return new ResponseEntity<>(curso, HttpStatus.CREATED);

	}

}
