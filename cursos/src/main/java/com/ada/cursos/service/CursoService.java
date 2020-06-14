package com.ada.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.CursoForm;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.repository.CursoRepository;
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
		curso.setModalidad(cursoForm.getModalidad());
		curso.setPrecio(cursoForm.getPrecio());
		curso.setCategoria(cursoForm.getCategoria());
		curso.setCupo(cursoForm.getCupo());
		curso.setCupoBecas(cursoForm.getCupoBecas());
		curso.setAbierto(cursoForm.isAbierto());
		curso.setEmpresa(empresa);
		
		return curso;
		
	}

}
