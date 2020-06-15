package com.ada.cursos.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.form.CursoForm;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.service.CursoService;
import com.ada.cursos.service.EmpresaService;
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
	private EmpresaService empServ;

	Logger log = Logger.getLogger(CursoRepository.class.getName());
	
	
	@GetMapping(path = "/{id}")
	@Operation(summary = "cursoPorId", description = "Recibe como un Long id y devuelve el Curso con el id correspondiente.")
	public ResponseEntity<Curso> cursoPorId(@PathVariable Long id) {

		log.info("Metodo cursoPorId: buscando curso id"+id);
		Curso curso = cursoServ.porId(id);		
		
		log.info("Curso encontrado");
		return new ResponseEntity<>(curso, HttpStatus.OK);
	}

	@GetMapping(path = "/listado")
	@Operation(summary = "listarCursos", description = "Lista todos los cursos presentes en la base de datos.")
	public ResponseEntity<List<Curso>> listarCursos() {

		log.info("Metodo listarCursos: listando cursos...");
		Iterable<Curso> ListCurIt = cursoRepo.findAll();
		List<Curso> listadoCursos = Lists.newArrayList(ListCurIt);

		log.info("Listado completo: listadoCursos.");
		return new ResponseEntity<>(listadoCursos, HttpStatus.OK);
	}

	@GetMapping(path = "/listado-abiertos")
	@Operation(summary = "listarAbiertos", description = "Lista todos los cursos que coinciden con abierto=true.")
	public ResponseEntity<List<Curso>> listarAbiertos() {

		log.info("Metodo listarAbiertos: listando cursos abiertos...");
		Iterable<Curso> ListCurIt = cursoRepo.findByAbiertoTrue();
		List<Curso> cursosAbiertos = Lists.newArrayList(ListCurIt);

		log.info("Listado completo: cursosAbiertos.");
		return new ResponseEntity<>(cursosAbiertos, HttpStatus.OK);
	}

	@GetMapping(path = "/listado-por-categoria")
	@Operation(summary = "listarPorCategoria", description = "Recibe como parámetro un String categoría y lista todos los cursos cuya categoría coincide.")
	public ResponseEntity<List<Curso>> listarPorCategoria(@RequestParam String categoria) {

		log.info("Metodo listarPorCategoria: listando cursos de la categoria " + categoria + "...");
		Iterable<Curso> ListCurIt = cursoRepo.findByCategoriaStartingWith(categoria);
		List<Curso> cursosPorCat = Lists.newArrayList(ListCurIt);
		
		log.info("Listado completo: cursosPorCat.");
		return new ResponseEntity<>(cursosPorCat, HttpStatus.OK);
	}

	@GetMapping(path = "/listado-por-empresa")
	@Operation(summary = "listarPorEmpresa", description = "Trae una lista de cursos por empresa")
	public ResponseEntity<List<Curso>> listarPorEmpresa(@RequestParam Long id) {

		log.info("Metodo listarPorEmpresa: buscando empresa id" + id);
		Empresa empresa = empServ.porId(id);
		
		log.info("listando cursos...");
		Iterable<Curso> ListCurIt = empresa.getCursos();
		List<Curso> cursosPorEmp = Lists.newArrayList(ListCurIt);
		
		log.info("Listado completo: cursosPorEmp");
		return new ResponseEntity<>(cursosPorEmp, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listado-por-empresa-y-categoria")
	@Operation(summary = "listarPorEmpresaYcategoria", description = "Trae una lista de cursos por empresa y la filtra por categoria")
	public ResponseEntity<List<Curso>> listarPorEmpresaYcategoria(@RequestParam Long id, @RequestParam String categoria) {

		log.info("Metodo listarPorEmpresaYcategoria: buscando empresa id" + id);
		Empresa empresa = empServ.porId(id);
		
		log.info("listando cursos...");
		Iterable<Curso> ListCurIt = empresa.getCursos();
		List<Curso> cursosPorEmp = Lists.newArrayList(ListCurIt);
		
		log.info("filtrando por categoria...");
		List<Curso> cursosPorEmpYcat = cursoServ.filtrarPorCategoria(cursosPorEmp, categoria);

		return new ResponseEntity<>(cursosPorEmpYcat, HttpStatus.OK);
	}


	@PostMapping(path = "/alta")
	@Operation(summary = "altaCurso", description = "Persiste un nuevo Curso en la base de datos.")

	public ResponseEntity<Curso> altaCurso(@RequestBody CursoForm cursoForm) {

		log.info("Metodo altaCurso: creando curso...");

		Curso curso = new Curso();
		curso = cursoServ.cargarDatosForm(cursoForm, curso);
		cursoRepo.save(curso);

		log.info("Curso guardado.");

		return new ResponseEntity<>(curso, HttpStatus.CREATED);

	}

	@PutMapping(path = "/update/{id}")
	@Operation(summary = "modificarCurso", description = "Recibe un Long id y un CursoForm, busca el curso por id y lo actualiza con los datos del formulario.")
	public ResponseEntity<Curso> modificarCurso(@RequestBody CursoForm cursoForm, @PathVariable Long id) {
		
		log.info("Metodo updateCurso: buscando curso...");
		Curso curso = cursoServ.porId(id);
		
		log.info("Modificando curso...");
		curso = cursoServ.cargarDatosForm(cursoForm, curso);
		cursoRepo.save(curso);
		
		log.info("Curso modificado.");
		return new ResponseEntity<>(curso, HttpStatus.OK);

	}

	@DeleteMapping(path = "/delete/{id}")
	@Operation(summary = "deleteCurso", description = "Recibe un Long id, busca el curso por id y borra de la base de datos.")
	public ResponseEntity<Object> deleteCurso(@PathVariable Long id) {
		
		log.info("Metodo deleteCurso: buscando curso...");
		Curso curso = cursoServ.porId(id);
		
		log.info("Borrando curso id  " + id);
		cursoRepo.delete(curso);

		log.info("Curso borrado.");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
