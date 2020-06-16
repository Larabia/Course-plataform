package com.ada.cursos.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.form.EmpresaForm;
import com.ada.cursos.form.RepForm;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.model.Rep;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.repository.RepRepository;
import com.ada.cursos.service.RepService;
import com.google.common.collect.Lists;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "/rep")
public class RepController {

	@Autowired
	private RepRepository repRepo;
	
	@Autowired
	private RepService repServ;
	
	Logger log = Logger.getLogger(AlumnoRepository.class.getName());

	
	@GetMapping(path = "/{id}")
	@Operation(summary = "repPorId", description = "Recibe un Long id y devuelve un objeto Rep con el id correspondiente.")
	public ResponseEntity<Rep> repPorId(@PathVariable Long id) {

		log.info("Metodo repPorId: buscando rep id"+id);
		Rep rep = repServ.porId(id);		
		
		log.info("Rep encontrado.");
		return new ResponseEntity<>(rep, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listado")
	@Operation(summary = "listarReps", description = "Lista todas los Reps presentes en la base de datos.")
	public ResponseEntity<List<Rep>> listarEmpresas() {

		log.info("Metodo listarReps: listando cursos...");
		Iterable<Rep> ListRepIt = repRepo.findAll();
		List<Rep> listadoReps = Lists.newArrayList(ListRepIt);

		log.info("Listado completo: listadoReps.");
		return new ResponseEntity<>(listadoReps, HttpStatus.OK);
	}


	@PostMapping(path = "/alta")
	@Operation (summary = "Alta Rep", description = "Ingrasa un objeto Rep a la base de datos")
	
	public ResponseEntity<Rep> altaRep(@RequestBody RepForm repForm) {	
		
		log.info("Metodo altaRep: creando rep...");
		
		Rep rep = new Rep();		
		rep = repServ.cargarDatosForm(repForm, rep);		
		repRepo.save(rep);
	
		log.info("Rep guardado.");

		return new ResponseEntity<>(rep, HttpStatus.CREATED);

	}
	
	@PutMapping(path = "/modificar/{id}")
	@Operation(summary = "modificarRep", description = "Recibe un Long id y un RepForm, busca el rep por id y lo actualiza con los datos del formulario.")
	public ResponseEntity<Rep> modificarRep(@RequestBody RepForm repForm, @PathVariable Long id) {
		
		log.info("Metodo modificarRep: buscando rep...");
		Rep rep = repServ.porId(id);
		
		log.info("Modificando rep...");
		rep = repServ.cargarDatosForm(repForm, rep);
		repRepo.save(rep);
		
		log.info("Rep modificado.");
		return new ResponseEntity<>(rep, HttpStatus.OK);

	}
}
