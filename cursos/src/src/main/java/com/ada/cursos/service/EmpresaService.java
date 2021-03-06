package com.ada.cursos.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.exceptions.IdInexistenteException;
import com.ada.cursos.form.EmpresaForm;
import com.ada.cursos.model.Empresa;
import com.ada.cursos.model.Rep;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.EmpresaRepository;
import com.ada.cursos.repository.RepRepository;
import com.google.common.collect.Lists;

@Service
public class EmpresaService {

	@Autowired
	private RepRepository repRepo;

	@Autowired
	private EmpresaRepository empresaRepo;

	Logger log = Logger.getLogger(CursoRepository.class.getName());

	public Empresa porId(Long id) throws IdInexistenteException {

		java.util.Optional<Empresa> empresaOp = empresaRepo.findById(id);

		if (Optional.empty().equals(empresaOp)) {
			throw new IdInexistenteException("El id ingresado no existe.");
		}

		Empresa empresa = empresaOp.get();

		return empresa;
	}

	public Empresa guardar(Empresa empresa) {

		empresaRepo.save(empresa);

		return empresa;
	}

	public void borrar(Empresa empresa) {

		empresaRepo.delete(empresa);
		log.info("Empresa borrada.");
	}

	public List<Empresa> listar() {

		Iterable<Empresa> ListEmpIt = empresaRepo.findAll();
		List<Empresa> listadoEmp = Lists.newArrayList(ListEmpIt);

		return listadoEmp;
	}

	public Empresa cargarDatosForm(EmpresaForm empresaForm, Empresa empresa) throws IdInexistenteException {

		java.util.Optional<Rep> repOp = repRepo.findById(empresaForm.getRepId());
		if (Optional.empty().equals(repOp)) {
			throw new IdInexistenteException("El id de rep ingresado no existe.");
		}

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
