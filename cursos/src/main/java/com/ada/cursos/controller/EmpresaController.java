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
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.form.EmpresaForm;
import com.ada.cursos.model.Empresa;

import com.ada.cursos.repository.EmpresaRepository;
import com.ada.cursos.service.EmpresaService;
import com.google.common.collect.Lists;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Autowired
	private EmpresaService empresaServ;
	
	Logger log = Logger.getLogger(EmpresaRepository.class.getName());

	@GetMapping(path = "/{id}")
	@Operation(summary = "empresaPorId", description = "Recibe como un Long id y devuelve un objeto Empresa con el id correspondiente.")
	public ResponseEntity<Empresa> empresaPorId(@PathVariable Long id) {

		log.info("Metodo empresaPorId: buscando curso id"+id);
		Empresa empresa = empresaServ.porId(id);		
		
		log.info("Empresa encontrada");
		return new ResponseEntity<>(empresa, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listado")
	@Operation(summary = "listarEmpresas", description = "Lista todas las empresas presentes en la base de datos.")
	public ResponseEntity<List<Empresa>> listarEmpresas() {

		log.info("Metodo listarCursos: listando cursos...");
		Iterable<Empresa> ListEmpIt = empresaRepo.findAll();
		List<Empresa> listadoEmpresas = Lists.newArrayList(ListEmpIt);

		log.info("Listado completo: listadoCursos.");
		return new ResponseEntity<>(listadoEmpresas, HttpStatus.OK);
	}

	@PostMapping(path = "/alta")
	@Operation (summary = "Alta Empresa", description = "Ingrasa un objeto Empresa a la base de datos")
	
	public ResponseEntity<Empresa> altaEmpresa(@RequestBody EmpresaForm empresaForm) {	
		
		log.info("Metodo altaEmpresa: creando empresa...");
		
		Empresa empresa = new Empresa();	
	    empresa = empresaServ.cargarDatosForm(empresaForm, empresa);
		empresaRepo.save(empresa);
		
		log.info("Empresa guardada.");

		return new ResponseEntity<>(empresa, HttpStatus.CREATED);

	}
	
	@PutMapping(path = "/modificar/{id}")
	@Operation(summary = "modificarEmpresa", description = "Recibe un Long id y un EmpresaForm, busca la empresa por id y la actualiza con los datos del formulario.")
	public ResponseEntity<Empresa> modificarEmpresa(@RequestBody EmpresaForm empresaForm, @PathVariable Long id) {
		
		log.info("Metodo modificarEmpresa: buscando empresa...");
		Empresa empresa = empresaServ.porId(id);
		
		log.info("Modificando empresa...");
		empresa = empresaServ.cargarDatosForm(empresaForm, empresa);
		empresaRepo.save(empresa);
		
		log.info("Empresa modificada.");
		return new ResponseEntity<>(empresa, HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/borrar/{id}")
	@Operation(summary = "borrarEmpresa", description = "Recibe un Long id, busca la empresa por id y la borra de la base de datos.")
	public ResponseEntity<Object> borrarEmpresa(@PathVariable Long id) {
		
		log.info("Metodo borrarEmpresa: buscando empresa...");
		Empresa empresa = empresaServ.porId(id);
		
		log.info("Borrando empresa id  " + id);
		empresaRepo.delete(empresa);

		log.info("Empresa borrada.");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
