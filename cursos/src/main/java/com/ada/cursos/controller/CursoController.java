package com.ada.cursos.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.form.CursoForm;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.EmpresaRepository;
import com.ada.cursos.service.CursoService;
import com.google.common.collect.Lists;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/curso")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepo;
	
	@Autowired
	private CursoService cursoServ;
	
	@Autowired
	private EmpresaRepository empRepo;

	Logger log = Logger.getLogger(CursoRepository.class.getName());

	
	
	@GetMapping(path = "/listado")
	@Operation(summary = "ListarCursos", description = "Trae una lista de cursos en la base")
    public ResponseEntity<List<Curso>> listarCursos() {
		
		log.info("comienzo invocacion listar cursos...");
		
		Iterable<Curso> ListCurIt = cursoRepo.findAll();
		List<Curso> cursosAbiertos = Lists.newArrayList(ListCurIt);
		
		return new ResponseEntity<>(cursosAbiertos, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listado-abiertos")
	@Operation(summary = "ListarAbiertos", description = "Trae una lista de cursos abiertos")
    public ResponseEntity<List<Curso>> listarAbiertos() {
		
		log.info("comienzo invocacion listar cursos...");
		
		Iterable<Curso> ListCurIt = cursoRepo.findByAbiertoTrue();
		List<Curso> cursosAbiertos = Lists.newArrayList(ListCurIt);
		
		return new ResponseEntity<>(cursosAbiertos, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/listado-por-categoria")
	@Operation(summary = "ListarPorCategoria", description = "Trae una lista de cursos por categoria")
    public ResponseEntity<List<Curso>> listarPorCategoria(@RequestParam String categoria) {
		
		log.info("comienzo invocacion listar cursos...");
		
		Iterable<Curso> ListCurIt = cursoRepo.findByCategoriaStartingWith(categoria);
		List<Curso> cursos = Lists.newArrayList(ListCurIt);
		
		return new ResponseEntity<>(cursos, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/listado-por-empresa")
	@Operation(summary = "ListarPorEmpresa", description = "Trae una lista de cursos por empresa")
    public ResponseEntity<List<Curso>> listarPorEmpresa(@RequestParam Long id) {
		
		log.info("comienzo invocacion listar cursos id empresa" + id);
		
		java.util.Optional<Empresa> empresaOp = empRepo.findById(id);
		Empresa empresa = empresaOp.get();
		Iterable<Curso> ListCurIt = empresa.getCursos();
		List<Curso> cursosPorEmp = Lists.newArrayList(ListCurIt);
		
		return new ResponseEntity<>(cursosPorEmp, HttpStatus.OK);
	}

	
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
