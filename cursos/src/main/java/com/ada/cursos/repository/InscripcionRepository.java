package com.ada.cursos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Inscripcion;

public interface InscripcionRepository extends CrudRepository<Inscripcion, Long> {
	
	public List<Curso> findByFinalizadoTrue();
	public List<Curso> findByFinalizadoFalse();

}