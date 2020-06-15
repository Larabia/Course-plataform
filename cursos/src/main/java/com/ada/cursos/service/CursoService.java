package com.ada.cursos.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.CursoForm;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.repository.CursoRepository;



@Service
public class CursoService {

	
	@Autowired
	private EmpresaService empService;

	@Autowired
	private CursoRepository cursoRepo;

	Logger log = Logger.getLogger(CursoRepository.class.getName());

	
	
	public Curso porId(Long id) {

		Optional<Curso> cursoOp = cursoRepo.findById(id);

		if (Optional.empty().equals(cursoOp)) {
			log.info("El id ingresado no existe.");
		}

		Curso curso = cursoOp.get();
		return curso;

	}

	public Curso generarCursoDeForm(CursoForm cursoForm, Curso curso) {
		
		Empresa empresa = empService.porId(cursoForm.getEmpId());

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
