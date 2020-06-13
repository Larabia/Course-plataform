package com.ada.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.EmpresaForm;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.model.Rep;
import com.ada.cursos.repository.RepRepository;


@Service
public class EmpresaService {
	
	@Autowired
	private RepRepository repRepo;
	
	public Empresa generarEmpresaDeForm(EmpresaForm empresaForm) {

		Empresa empresa = new Empresa();

		java.util.Optional<Rep> repOp = repRepo.findById(empresaForm.getRepId());
		Rep rep = repOp.get();

		empresa.setNombre(empresaForm.getNombre());
		empresa.setCuil(empresaForm.getCuil());
		
		empresa.setRep(rep);
		
		return empresa;

	}


}
