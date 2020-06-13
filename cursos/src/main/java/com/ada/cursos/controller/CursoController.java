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
import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.DatosSE;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.EmpresaRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/cursoController")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepo;
	
	@Autowired
	private EmpresaRepository empRepo;

	Logger log = Logger.getLogger(CursoRepository.class.getName());

	// POST

	@PostMapping(path = "/alta")
	@Operation(summary = "Alta curso", description = "Ingrasa un objeto curso a la base de datos")

	public ResponseEntity<Curso> altaCurso(@RequestBody CursoForm cursoForm) {

		log.info("metodo: altaCurso.");
		Curso curso = new Curso();

		java.util.Optional<Empresa> empresaOP = empRepo.findById(cursoForm.getEmpresaId());
		Empresa empresa = empresaOP.get();
		
		curso.setNombre(cursoForm.getNombre());
		curso.setHoras(cursoForm.getHoras());
		curso.setEmpresa(empresa);

		cursoRepo.save(curso);
		log.info("metodo: Curso guardado.");

		return new ResponseEntity<>(curso, HttpStatus.CREATED);

	}

}
