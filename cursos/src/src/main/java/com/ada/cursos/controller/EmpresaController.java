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
import com.ada.cursos.form.EmpresaForm;
import com.ada.cursos.model.Empresa;

import com.ada.cursos.repository.EmpresaRepository;
import com.ada.cursos.service.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaServ;

	Logger log = Logger.getLogger(EmpresaRepository.class.getName());

	@GetMapping(path = "/{id}")
	@Operation(summary = "empresaPorId", description = "Recibe como un Long id y devuelve un objeto Empresa con el id correspondiente.")
	@PreAuthorize("hasRole('REP')")
	public ResponseEntity<Empresa> empresaPorId(@PathVariable Long id) {

		log.info("Metodo empresaPorId: buscando curso id" + id);
		Empresa empresa;
		try {
			empresa = empresaServ.porId(id);

			log.info("Empresa encontrada");
			return new ResponseEntity<>(empresa, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/")
	@Operation(summary = "listarEmpresas", description = "Lista todas las empresas presentes en la base de datos.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Empresa>> listarEmpresas() {

		log.info("Metodo listarCursos: listando cursos...");
		List<Empresa> listadoEmpresas = empresaServ.listar();

		log.info("Listado completo: listadoEmpresas.");
		return new ResponseEntity<>(listadoEmpresas, HttpStatus.OK);
	}

	@PostMapping(path = "/")
	@Operation(summary = "Alta Empresa", description = "Ingrasa un objeto Empresa a la base de datos")
	@PreAuthorize("hasRole('REP')")
	public ResponseEntity<Empresa> altaEmpresa(@RequestBody EmpresaForm empresaForm) {

		log.info("Metodo altaEmpresa: creando empresa...");

		Empresa empresa = new Empresa();
		try {
			empresa = empresaServ.cargarDatosForm(empresaForm, empresa);

			empresaServ.guardar(empresa);

			log.info("Empresa guardada.");

			return new ResponseEntity<>(empresa, HttpStatus.CREATED);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = "/aprobar/{id}")
	@Operation(summary = "aprobarEmpresa", description = "Recibe un Long id, busca la empresa por id y modifica isAprobada = true.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Empresa> aprobarEmpresa(@PathVariable Long id) {

		log.info("Metodo aprobarEmpresa: buscando empresa...");
		Empresa empresa;
		try {
			empresa = empresaServ.porId(id);

			log.info("Aprobando empresa...");
			empresa.setAprobada(true);
			empresaServ.guardar(empresa);

			log.info("Empresa aprobada.");
			return new ResponseEntity<>(empresa, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(path = "/")
	@Operation(summary = "modificarEmpresa", description = "Recibe un Long id y un EmpresaForm, busca la empresa por id y la actualiza con los datos del formulario.")
	@PreAuthorize("hasRole('REP')")
	public ResponseEntity<Empresa> modificarEmpresa(@RequestBody EmpresaForm empresaForm) {

		log.info("Metodo modificarEmpresa: buscando empresa...");
		Empresa empresa;
		try {
			empresa = empresaServ.porId(empresaForm.getId());

			log.info("Modificando empresa...");
			empresa = empresaServ.cargarDatosForm(empresaForm, empresa);
			empresaServ.guardar(empresa);

			log.info("Empresa modificada.");
			return new ResponseEntity<>(empresa, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(path = "/{id}")
	@Operation(summary = "borrarEmpresa", description = "Recibe un Long id, busca la empresa por id y la borra de la base de datos.")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> borrarEmpresa(@PathVariable Long id) {

		log.info("Metodo borrarEmpresa: buscando empresa...");
		Empresa empresa;
		try {
			empresa = empresaServ.porId(id);

			log.info("Borrando empresa id  " + id);
			empresaServ.borrar(empresa);

			log.info("Empresa borrada.");
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (IdInexistenteException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
