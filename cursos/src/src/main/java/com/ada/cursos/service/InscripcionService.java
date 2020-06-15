package com.ada.cursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.InscripcionForm;
import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Inscripcion;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.UsuarioRepository;


@Service
public class InscripcionService {
	
	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	AlumnoRepository alumnoRepo;

	@Autowired
	CursoRepository cursoRepo;
	
	public Inscripcion generarInscripcionDeForm (InscripcionForm inscripcionForm) {
		
		Inscripcion inscripcion = new Inscripcion();
		
		java.util.Optional<Alumno> alumnoOp = alumnoRepo.findById(inscripcionForm.getAlumnoId());
		Alumno alumno = alumnoOp.get();
		
		java.util.Optional<Curso> cursoOp = cursoRepo.findById(inscripcionForm.getCursoId());
		Curso curso = cursoOp.get();
		
		inscripcion.setAlumno(alumno);
		inscripcion.setCurso(curso);
		inscripcion.setSolicitaBeca(inscripcionForm.isSolicitaBeca());
		
		return inscripcion;
	}

}
