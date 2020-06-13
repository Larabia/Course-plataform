package com.ada.cursos.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.cursos.form.EmpresaForm;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.model.Rep;


import com.ada.cursos.repository.EmpresaRepository;
import com.ada.cursos.repository.RepRepository;
import com.ada.cursos.service.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Autowired
	private EmpresaService empresaServ;
	
	Logger log = Logger.getLogger(EmpresaRepository.class.getName());

	// POST

	@PostMapping(path = "/alta")
	@Operation (summary = "Alta Empresa", description = "Ingrasa un objeto Empresa a la base de datos")
	
	public ResponseEntity<Empresa> altaEmpresa(@RequestBody EmpresaForm empresaForm) {	
		
		log.info("metodo: altaEmpresa.");
		
		Empresa empresa = empresaServ.generarEmpresaDeForm(empresaForm);
		empresaRepo.save(empresa);
		
		log.info("metodo: Empresa guardada.");

		return new ResponseEntity<>(empresa, HttpStatus.CREATED);

	}

}
