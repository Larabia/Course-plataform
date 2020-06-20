package com.ada.cursos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.Inscripcion;

public interface InscripcionRepository extends CrudRepository<Inscripcion, Long> {
	
	public List<Inscripcion> findByFinalizadoTrue();
	public List<Inscripcion> findByFinalizadoFalse();
	public List<Inscripcion> findByAlumno(Alumno alumno);

}