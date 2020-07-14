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
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.exceptions.IdInexistenteException;
import com.ada.cursos.form.RepForm;
import com.ada.cursos.model.Rep;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.service.RepService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/rep")
public class RepController {

	@Autowired
	private RepService repServ;

	Logger log = Logger.getLogger(AlumnoRepository.class.getName());

	@GetMapping(path = "/{id}")
	@PreAuthorize("hasRole('REP')")
	@Operation(summary = "repPorId", description = "Recibe un Long id y devuelve un objeto Rep con el id correspondiente.")
	public ResponseEntity<Rep> repPorId(@PathVariable Long id) {

		log.info("Metodo repPorId: buscando rep id" + id);
		Rep rep;
		try {
			rep = repServ.porId(id);
			log.info("Rep encontrado.");
			return new ResponseEntity<>(rep, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/")
	@Operation(summary = "listarReps", description = "Lista todas los Reps presentes en la base de datos.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Rep>> listarReps() {

		log.info("Metodo listarReps: listando reps...");
		List<Rep> listadoReps = repServ.listar();

		log.info("Listado completo: listadoReps.");
		return new ResponseEntity<>(listadoReps, HttpStatus.OK);
	}

	@PostMapping(path = "/")
	@Operation(summary = "Alta Rep", description = "Ingrasa un objeto Rep a la base de datos")
	@PreAuthorize("hasRole('REP')")
	public ResponseEntity<Rep> altaRep(@RequestBody RepForm repForm) {

		log.info("Metodo altaRep: creando rep...");

		Rep rep = new Rep();
		try {
			rep = repServ.cargarDatosForm(repForm, rep);

			repServ.guardar(rep);

			log.info("Rep guardado.");

			return new ResponseEntity<>(rep, HttpStatus.CREATED);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = "/")
	@Operation(summary = "modificarRep", description = "Recibe un Long id y un RepForm, busca el rep por id y lo actualiza con los datos del formulario.")
	@PreAuthorize("hasRole('REP')")
	public ResponseEntity<Rep> modificarRep(@RequestBody RepForm repForm) {

		log.info("Metodo modificarRep: buscando rep...");
		Rep rep;
		try {
			rep = repServ.porId(repForm.getId());

			log.info("Modificando rep...");
			rep = repServ.cargarDatosForm(repForm, rep);
			repServ.guardar(rep);

			log.info("Rep modificado.");
			return new ResponseEntity<>(rep, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(path = "/{id}")
	@Operation(summary = "borrarRep", description = "Recibe un Long id, busca el Rep por id y lo borra de la base de datos.")
	@PreAuthorize("hasRole('REP')")
	public ResponseEntity<Object> borrarRep(@PathVariable Long id) {

		log.info("Metodo borrarRep: buscando rep...");
		Rep rep;
		try {
			rep = repServ.porId(id);

			log.info("Borrando rep id  " + id);
			repServ.borrar(rep);

			log.info("Rep borrado.");
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
