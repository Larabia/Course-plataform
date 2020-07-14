package com.ada.cursos.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.exceptions.IdInexistenteException;
import com.ada.cursos.form.CursoForm;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.repository.CursoRepository;
import com.google.common.collect.Lists;

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
	
	public Curso guardar(Curso curso) {

		cursoRepo.save(curso);

		return curso;
	}

	public void borrar(Curso curso) {

		cursoRepo.delete(curso);
		log.info("Curso borrado.");
	}

	public List<Curso> listar() {

		Iterable<Curso> ListCursoIt = cursoRepo.findAll();
		List<Curso> listadoCurso = Lists.newArrayList(ListCursoIt);

		return listadoCurso;
	}
	

	public Curso cargarDatosForm(CursoForm cursoForm, Curso curso) throws IdInexistenteException {

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
	
	public List<Curso> listarAbiertos() {

		Iterable<Curso> ListCurIt = cursoRepo.findByAbiertoTrue();
		List<Curso> cursosAbiertos = Lists.newArrayList(ListCurIt);

		return cursosAbiertos;
	}
	
	public List<Curso> listarConCategoria(String categoria) {

		Iterable<Curso> ListCurIt = cursoRepo.findByCategoriaStartingWith(categoria);
		List<Curso> cursosPorCat = Lists.newArrayList(ListCurIt);

		return cursosPorCat;
	}


	public List<Curso> filtrarPorCategoria(List<Curso> cursosPorEmp, String categoria) {

		List<Curso> cursosPorEmpYcat = new ArrayList<Curso>();
		Iterator<Curso> filtrarPorCat = cursosPorEmp.iterator();

		while (filtrarPorCat.hasNext()) {
			Curso curso = filtrarPorCat.next();
			if (categoria.equals(curso.getCategoria())) {
				cursosPorEmpYcat.add(curso);
			}
		}

		return cursosPorEmpYcat;
	}

	public boolean empresaAprobada(Curso curso) {

		Empresa empresa = curso.getEmpresa();

		if (empresa.isAprobada()) {
			return true;
		} else {
			return false;
		}
	}

}
