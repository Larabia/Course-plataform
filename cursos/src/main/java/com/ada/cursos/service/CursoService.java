package com.ada.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.CursoForm;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.repository.EmpresaRepository;


@Service
public class CursoService {
	
	@Autowired
	private EmpresaRepository empRepo;
	
	public Curso generarCursoDeForm (CursoForm cursoForm) {
		
		Curso curso = new Curso();

		java.util.Optional<Empresa> empresaOP = empRepo.findById(cursoForm.getEmpId());
		Empresa empresa = empresaOP.get();
		
		curso.setNombre(cursoForm.getNombre());
		curso.setHoras(cursoForm.getHoras());
		curso.setEmpresa(empresa);
		
		return curso;
		
	}

}
