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

import com.ada.cursos.form.AlumnoForm;
import com.ada.cursos.form.CursoForm;
import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.model.Inscripcion;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.repository.InscripcionRepository;
import com.ada.cursos.service.AlumnoService;
import com.ada.cursos.service.InscripcionService;
import com.google.common.collect.Lists;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/alumno")
public class AlumnoController {
	
	@Autowired
	private AlumnoRepository alumnoRepo;
	
	@Autowired
	private AlumnoService alumnoServ;
	
	@Autowired
	private InscripcionRepository inscRepo;
	

	
	Logger log = Logger.getLogger(AlumnoRepository.class.getName());

	@GetMapping(path = "/{id}")
	@Operation(summary = "alumnoPorId", description = "Recibe como un Long id y devuelve el alumno con el id correspondiente.")
	public ResponseEntity<Alumno> alumnoPorId(@PathVariable Long id) {

		log.info("Metodo alumnoPorId: buscando alumno id"+id);
		Alumno alumno = alumnoServ.porId(id);		
		
		log.info("Alumno encontrado");
		return new ResponseEntity<>(alumno, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listado")
	@Operation(summary = "listarAlumnos", description = "Lista todos los alumnos presentes en la base de datos.")
	public ResponseEntity<List<Alumno>> listarAlumnos() {

		log.info("Metodo listarAlumnos: listando alumnos...");
		Iterable<Alumno> ListAlumIt = alumnoRepo.findAll();
		List<Alumno> listadoAlumnos = Lists.newArrayList(ListAlumIt);

		log.info("Listado completo: listadoAlumnos.");
		return new ResponseEntity<>(listadoAlumnos, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/cursos-en-progreso")
	@Operation(summary = "listarCursosEnProgreso", description = "Trae una lista de cursos por alumno y la filtra por finalizado = false")
	public ResponseEntity<List<Curso>> listarCursosEnProgreso(@RequestParam Long id) {

		log.info("Metodo listarCursosEnProgreso: buscando alumno id" + id);
		Alumno alumno = alumnoServ.porId(id);
		
		log.info("listando cursos...");
		Iterable<Inscripcion> ListInscIt = alumno.getInscripciones();
		List<Inscripcion> inscripciones = Lists.newArrayList(ListInscIt);
		
		log.info("filtrando por categoria...");
		List<Curso> cursosEnProgreso = alumnoServ.filtrarPorFinalizadoFalse(inscripciones);

		return new ResponseEntity<>(cursosEnProgreso, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/cursos-finalizados")
	@Operation(summary = "listarCursosFinalizados", description = "Trae una lista de cursos por alumno y la filtra por finalizado = true")
	public ResponseEntity<List<Curso>> listarCursosFinalizados(@RequestParam Long id) {

		log.info("Metodo listarCursosFinalizados: buscando alumno id" + id);
		Alumno alumno = alumnoServ.porId(id);
		
		log.info("listando cursos...");
		Iterable<Inscripcion> ListInscIt = alumno.getInscripciones();
		List<Inscripcion> inscripciones = Lists.newArrayList(ListInscIt);
		
		log.info("filtrando por categoria...");
		List<Curso> cursosFinalizados= alumnoServ.filtrarPorFinalizadoTrue(inscripciones);

		return new ResponseEntity<>(cursosFinalizados, HttpStatus.OK);
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
	
	
	@PutMapping(path = "/modificar/{id}")
	@Operation(summary = "modificarAlumno", description = "Recibe un Long id y un AlumnoForm, busca el alumno por id y lo actualiza con los datos del formulario.")
	public ResponseEntity<Alumno> modificarAlumno(@RequestBody AlumnoForm alumnoForm, @PathVariable Long id) {
		
		log.info("Metodo modificarAlumno: buscando alumno...");
		Alumno alumno = alumnoServ.porId(id);
		
		log.info("Modificando alumno...");
		alumno = alumnoServ.cargarDatosForm(alumnoForm, alumno);
		alumnoRepo.save(alumno);
		
		log.info("Alumno modificado.");
		return new ResponseEntity<>(alumno, HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/borrar/{id}")
	@Operation(summary = "borrarAlumno", description = "Recibe un Long id, busca el Alumno por id y lo borra de la base de datos.")
	public ResponseEntity<Object> borrarAlumno(@PathVariable Long id) {
		
		log.info("Metodo borrarAlumno: buscando curso...");
		Alumno alumno = alumnoServ.porId(id);
		
		log.info("Borrando alumno id  " + id);
		alumnoRepo.delete(alumno);

		log.info("Alumno borrado.");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	

}