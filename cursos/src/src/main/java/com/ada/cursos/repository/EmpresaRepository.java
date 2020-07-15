package com.ada.cursos.repository;


import org.springframework.data.repository.CrudRepository;

import com.ada.cursos.model.Empresa;


public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
		
	
}
