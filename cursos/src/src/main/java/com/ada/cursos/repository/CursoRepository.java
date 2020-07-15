package com.ada.cursos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ada.cursos.model.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {
		
	public List<Curso> findByAbiertoTrue();
	
	public List<Curso> findByCategoriaStartingWith(String categoria);
}
