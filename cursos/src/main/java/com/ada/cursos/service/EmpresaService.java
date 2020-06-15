package com.ada.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.EmpresaForm;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.model.Rep;
import com.ada.cursos.repository.EmpresaRepository;
import com.ada.cursos.repository.RepRepository;


@Service
public class EmpresaService {
	
	@Autowired
	private RepRepository repRepo;
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
   
	public Empresa porId(Long id) {
		
		java.util.Optional<Empresa> empresaOp = empresaRepo.findById(id);
		Empresa empresa = empresaOp.get();
		
		return empresa;
	}
	
	public Empresa generarEmpresaDeForm(EmpresaForm empresaForm) {

		Empresa empresa = new Empresa();

		java.util.Optional<Rep> repOp = repRepo.findById(empresaForm.getRepId());
		Rep rep = repOp.get();

		empresa.setNombre(empresaForm.getNombre());
		empresa.setCuil(empresaForm.getCuil());
		empresa.setTipo(empresaForm.getTipo());
		empresa.setDir(empresaForm.getDir());
		empresa.setCategoria(empresaForm.getCategoria());
		empresa.setAñoFun(empresaForm.getAñoFun());
		empresa.setTel(empresaForm.getTel());		
		empresa.setRep(rep);
		
		return empresa;

	}


}
