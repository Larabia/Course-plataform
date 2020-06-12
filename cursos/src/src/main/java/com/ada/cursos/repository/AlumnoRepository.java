package com.ada.cursos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ada.cursos.model.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
