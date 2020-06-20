package com.ada.cursos.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.bind.annotation.RestController;


import com.ada.cursos.form.InscripcionForm;
import com.ada.cursos.model.Inscripcion;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.InscripcionRepository;
import com.ada.cursos.repository.UsuarioRepository;
import com.ada.cursos.service.InscripcionService;
import com.google.common.collect.Lists;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/inscripcion")
public class InscripcionController {

	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	AlumnoRepository alumnoRepo;

	@Autowired
	CursoRepository cursoRepo;

	@Autowired
	InscripcionRepository inscripcionRepo;

	@Autowired
	InscripcionService inscripcionServ;

	Log log = LogFactory.getLog(InscripcionController.class);

	
	@GetMapping(path = "/{id}")
	@Operation(summary = "inscripcionPorId", description = "Recibe como un Long id y devuelve la inscripcion con el id correspondiente.")
	public ResponseEntity<Inscripcion> inscripcionPorId(@PathVariable Long id) {

		log.info("Metodo inscripcionPorId: buscando inscripcion id" + id);
		Inscripcion inscripcion = inscripcionServ.porId(id);

		log.info("Inscripcion encontrado");
		return new ResponseEntity<>(inscripcion, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listado")
	@Operation(summary = "listarInscripciones", description = "Lista todos las inscripciones presentes en la base de datos.")
	public ResponseEntity<List<Inscripcion>> listarInscripciones() {

		log.info("Metodo listarInscripciones: listando inscripciones...");
		Iterable<Inscripcion> ListInscIt = inscripcionRepo.findAll();
		List<Inscripcion> listadoInscripciones = Lists.newArrayList(ListInscIt);

		log.info("Listado completo: listadoInscripciones.");
		return new ResponseEntity<>(listadoInscripciones, HttpStatus.OK);
	}

	@PostMapping(path = "/alta")
	@Operation(summary = "Alta Inscripcion", description = "Ingrasa un objeto Inscripcion a la base de datos")

	public ResponseEntity<Inscripcion> altaInscripcion(@RequestBody InscripcionForm inscripcionForm) {

		log.info("metodo: altaInscripcion.");

		Inscripcion inscripcion = new Inscripcion();
		
		inscripcionServ.actualizarCupo(inscripcionForm);
		inscripcion = inscripcionServ.cargarDatosForm(inscripcionForm, inscripcion);
		inscripcionRepo.save(inscripcion);

		log.info("metodo: Inscripcion guardada.");

		return new ResponseEntity<>(inscripcion, HttpStatus.CREATED);

	}
	
	@PutMapping(path = "/modificar/{id}")
	@Operation(summary = "modificarInscripcion", description = "Recibe un Long id y un InscripcionForm, busca la inscripcion por id y lo actualiza con los datos del formulario.")
	public ResponseEntity<Inscripcion> modificarInscripcion(@RequestBody InscripcionForm inscripcionForm, @PathVariable Long id) {
		
		log.info("Metodo modificarInscripcion: buscando inscripcion...");
		Inscripcion inscripcion = inscripcionServ.porId(id);
		
		log.info("Modificando inscripcion...");
		inscripcion = inscripcionServ.cargarDatosForm(inscripcionForm, inscripcion);
		inscripcionRepo.save(inscripcion);
		
		log.info("Inscripcion modificada.");
		return new ResponseEntity<>(inscripcion, HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/borrar/{id}")
	@Operation(summary = "borrarInscripcion", description = "Recibe un Long id, busca la Inscripcion por id y la borra de la base de datos.")
	public ResponseEntity<Object> borrarInscripcion(@PathVariable Long id) {
		
		log.info("Metodo borrarInscripcion: buscando inscripcion...");
		Inscripcion inscripcion = inscripcionServ.porId(id);
		
		log.info("Borrando inscripcion id  " + id);
		inscripcionRepo.delete(inscripcion);

		log.info("Inscripcion borrada.");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}


}
