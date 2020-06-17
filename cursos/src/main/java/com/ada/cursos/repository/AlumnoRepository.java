package com.ada.cursos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.Curso;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
	

}
