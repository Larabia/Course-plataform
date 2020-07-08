package com.ada.cursos.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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


import com.ada.cursos.form.InscripcionForm;
import com.ada.cursos.model.Inscripcion;
import com.ada.cursos.service.InscripcionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/inscripcion")
public class InscripcionController {

	
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
	
	@GetMapping(path = "/")
	@Operation(summary = "listarInscripciones", description = "Lista todos las inscripciones presentes en la base de datos.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Inscripcion>> listarInscripciones() {

		log.info("Metodo listarInscripciones: listando inscripciones...");
		List<Inscripcion> listadoInscripciones = inscripcionServ.listar();

		log.info("Listado completo: listadoInscripciones.");
		return new ResponseEntity<>(listadoInscripciones, HttpStatus.OK);
	}

	@PostMapping(path = "/")
	@Operation(summary = "Alta Inscripcion", description = "Ingrasa un objeto Inscripcion a la base de datos")
	public ResponseEntity<Inscripcion> altaInscripcion(@RequestBody InscripcionForm inscripcionForm) {

		Inscripcion inscripcion = new Inscripcion();
			
		log.info("metodo: altaInscripcion.");
		
		inscripcion = inscripcionServ.cargarDatosForm(inscripcionForm, inscripcion);

		if (inscripcionServ.cumpleRequisitos(inscripcion)) {
		
		inscripcionServ.guardar(inscripcion);
		inscripcionServ.actualizarCupo(inscripcion);

		log.info("metodo: Inscripcion guardada.");

		return new ResponseEntity<>(inscripcion, HttpStatus.CREATED);
		} else {
			
			log.info("metodo: la inscripcion no cumple con los requisitos.");
			return new ResponseEntity<>(inscripcion, HttpStatus.BAD_REQUEST);
		}

	}
	
	@PutMapping(path = "/")
	@Operation(summary = "modificarInscripcion", description = "Recibe un Long id y un InscripcionForm, busca la inscripcion por id y lo actualiza con los datos del formulario.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Inscripcion> modificarInscripcion(@RequestBody InscripcionForm inscripcionForm) {
		
		log.info("Metodo modificarInscripcion: buscando inscripcion...");
		Inscripcion inscripcion = inscripcionServ.porId(inscripcionForm.getId());
		
		log.info("Modificando inscripcion...");
		inscripcion = inscripcionServ.cargarDatosForm(inscripcionForm, inscripcion);
		inscripcionServ.guardar(inscripcion);	
		inscripcionServ.actualizarCupo(inscripcion);
		
		log.info("Inscripcion modificada.");
		return new ResponseEntity<>(inscripcion, HttpStatus.OK);

	}
	
	@PutMapping(path = "/aprobar/{id}")
	@Operation(summary = "aprobarBeca", description = "Recibe un Long id y un porcentBeca, busca la inscripcion por id y acrualiza isBecaAprobada y porcentBeca .")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Inscripcion> aprobarBeca(@RequestParam String porcentBeca, @PathVariable Long id) {
		
		log.info("Metodo aprobarBeca: buscando inscripcion...");
		Inscripcion inscripcion = inscripcionServ.porId(id);
		
		log.info("Aprobando beca...");
		if (inscripcionServ.cumpleRequisitos(inscripcion)) {
		inscripcion.setBecaAprobada(true);
		inscripcion.setPorcentBeca(porcentBeca);
		inscripcionServ.guardar(inscripcion);	
		inscripcionServ.actualizarCupo(inscripcion);
		
		log.info("Beca aprobada.");
		return new ResponseEntity<>(inscripcion, HttpStatus.OK);
		
		}else {
			log.info("No cumple requisitos.");
			return new ResponseEntity<>(inscripcion, HttpStatus.METHOD_NOT_ALLOWED);
		}

	}
	
	@PutMapping(path = "/cursada/{id}")
	@Operation(summary = "finalizarCursada", description = "Recibe un Long id, busca la inscripcion por id y acrualiza isFinalizado .")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Inscripcion> finalizarCursada(@PathVariable Long id) {
		
		log.info("Metodo finalizarCursada: buscando inscripcion...");
		Inscripcion inscripcion = inscripcionServ.porId(id);
		
		log.info("Finalizando cursada...");
		inscripcion.setFinalizado(true);
		inscripcionServ.guardar(inscripcion);	
		inscripcionServ.actualizarCupo(inscripcion);
		
		log.info("Cursada finalizada.");
		return new ResponseEntity<>(inscripcion, HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/{id}")
	@Operation(summary = "borrarInscripcion", description = "Recibe un Long id, busca la Inscripcion por id y la borra de la base de datos.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> borrarInscripcion(@PathVariable Long id) {
		
		log.info("Metodo borrarInscripcion: buscando inscripcion...");
		Inscripcion inscripcion = inscripcionServ.porId(id);
		
		log.info("Borrando inscripcion id  " + id);
		inscripcionServ.borrar(inscripcion);

		log.info("Inscripcion borrada.");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}


}
