package com.ada.cursos.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.exceptions.IdInexistenteException;
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
	private CursoService cursoServ;

	@Autowired
	private EmpresaService empServ;

	Logger log = Logger.getLogger(CursoRepository.class.getName());

	@GetMapping(path = "/{id}")
	@Operation(summary = "cursoPorId", description = "Recibe como un Long id y devuelve el Curso con el id correspondiente.")
	public ResponseEntity<Curso> cursoPorId(@PathVariable Long id) {

		log.info("Metodo cursoPorId: buscando curso id" + id);
		Curso curso;
		try {
			curso = cursoServ.porId(id);

			log.info("Curso encontrado");
			return new ResponseEntity<>(curso, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/")
	@Operation(summary = "listarCursos", description = "Lista todos los cursos presentes en la base de datos.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Curso>> listarCursos() {

		log.info("Metodo listarCursos: listando cursos...");
		List<Curso> listadoCursos = cursoServ.listar();

		log.info("Listado completo: listadoCursos.");
		return new ResponseEntity<>(listadoCursos, HttpStatus.OK);
	}

	@GetMapping(path = "/abiertos")
	@Operation(summary = "listarAbiertos", description = "Lista todos los cursos que coinciden con abierto=true.")
	public ResponseEntity<List<Curso>> listarAbiertos() {

		log.info("Metodo listarAbiertos: listando cursos abiertos...");
		List<Curso> cursosAbiertos = cursoServ.listarAbiertos();

		log.info("Listado completo: cursosAbiertos.");
		return new ResponseEntity<>(cursosAbiertos, HttpStatus.OK);
	}

	@GetMapping(path = "/categoria")
	@Operation(summary = "listarPorCategoria", description = "Recibe como parámetro un String categoría y lista todos los cursos cuya categoría coincide.")
	public ResponseEntity<List<Curso>> listarPorCategoria(@RequestParam String categoria) {

		log.info("Metodo listarPorCategoria: listando cursos de la categoria " + categoria + "...");
		List<Curso> cursosPorCat = cursoServ.listarConCategoria(categoria);

		log.info("Listado completo: cursosPorCat.");
		return new ResponseEntity<>(cursosPorCat, HttpStatus.OK);
	}

	@GetMapping(path = "/empresa")
	@Operation(summary = "listarPorEmpresa", description = "Trae una lista de cursos por empresa")
	public ResponseEntity<List<Curso>> listarPorEmpresa(@RequestParam Long id) {

		log.info("Metodo listarPorEmpresa: buscando empresa id" + id);
		Empresa empresa;
		try {
			empresa = empServ.porId(id);

			log.info("listando cursos...");
			Iterable<Curso> ListCurIt = empresa.getCursos();
			List<Curso> cursosPorEmp = Lists.newArrayList(ListCurIt);

			log.info("Listado completo: cursosPorEmp");
			return new ResponseEntity<>(cursosPorEmp, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/empresa-y-categoria")
	@Operation(summary = "listarPorEmpresaYcategoria", description = "Trae una lista de cursos por empresa y la filtra por categoria")
	public ResponseEntity<List<Curso>> listarPorEmpresaYcategoria(@RequestParam Long id,
			@RequestParam String categoria) {

		log.info("Metodo listarPorEmpresaYcategoria: buscando empresa id" + id);
		Empresa empresa;
		try {
			empresa = empServ.porId(id);

			log.info("listando cursos...");
			Iterable<Curso> ListCurIt = empresa.getCursos();
			List<Curso> cursosPorEmp = Lists.newArrayList(ListCurIt);

			log.info("filtrando por categoria...");
			List<Curso> cursosPorEmpYcat = cursoServ.filtrarPorCategoria(cursosPorEmp, categoria);

			return new ResponseEntity<>(cursosPorEmpYcat, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/")
	@Operation(summary = "altaCurso", description = "Persiste un nuevo Curso en la base de datos.")
	@PreAuthorize("hasRole('REP')")
	public ResponseEntity<Curso> altaCurso(@RequestBody CursoForm cursoForm) {

		log.info("Metodo altaCurso: creando curso...");

		Curso curso = new Curso();
		try {
			curso = cursoServ.cargarDatosForm(cursoForm, curso);

			if (cursoServ.empresaAprobada(curso)) {
				cursoServ.guardar(curso);
				log.info("Curso guardado.");
				return new ResponseEntity<>(curso, HttpStatus.CREATED);

			} else {

				log.info("La empresa aun no fue aprobada por el admin.");
				return new ResponseEntity<>(curso, HttpStatus.METHOD_NOT_ALLOWED);
			}

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = "/")
	@Operation(summary = "modificarCurso", description = "Recibe un Long id y un CursoForm, busca el curso por id y lo actualiza con los datos del formulario.")
	@PreAuthorize("hasRole('REP')")
	public ResponseEntity<Curso> modificarCurso(@RequestBody CursoForm cursoForm) {

		log.info("Metodo modificarCurso: buscando curso...");
		Curso curso;
		try {
			curso = cursoServ.porId(cursoForm.getId());

			log.info("Modificando curso...");
			curso = cursoServ.cargarDatosForm(cursoForm, curso);
			cursoServ.guardar(curso);

			log.info("Curso modificado.");
			return new ResponseEntity<>(curso, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(path = "/{id}")
	@Operation(summary = "borrarCurso", description = "Recibe un Long id, busca el curso por id y borra de la base de datos.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> borrarCurso(@PathVariable Long id) {

		log.info("Metodo borrarCurso: buscando curso...");
		Curso curso;
		try {
			curso = cursoServ.porId(id);

			log.info("Borrando curso id  " + id);
			cursoServ.borrar(curso);

			log.info("Curso borrado.");
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
