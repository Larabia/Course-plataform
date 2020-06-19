package com.ada.cursos.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.InscripcionForm;
import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.Curso;
import com.ada.cursos.model.Inscripcion;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.InscripcionRepository;
import com.ada.cursos.repository.UsuarioRepository;


@Service
public class InscripcionService {
	
	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	AlumnoRepository alumnoRepo;

	@Autowired
	CursoRepository cursoRepo;
	
	@Autowired
	InscripcionRepository inscripRepo;
	
	
	Logger log = Logger.getLogger(CursoRepository.class.getName());
	
    public Inscripcion porId(Long id) {
		
		Optional<Inscripcion> inscripcionOp = inscripRepo.findById(id);
		
		if (Optional.empty().equals(inscripcionOp)) {
			log.info("El id ingresado no existe.");
		}
		
		Inscripcion inscripcion = inscripcionOp.get();
		return inscripcion;
	}
	
	public Inscripcion cargarDatosForm (InscripcionForm inscripcionBform, Inscripcion inscripcion) {
				
		java.util.Optional<Alumno> alumnoOp = alumnoRepo.findById(inscripcionBform.getAlumnoId());
		if (Optional.empty().equals(alumnoOp)) {
			log.info("El id de alumno ingresado no existe.");
		}
		Alumno alumno = alumnoOp.get();
		
		java.util.Optional<Curso> cursoOp = cursoRepo.findById(inscripcionBform.getCursoId());
		if (Optional.empty().equals(cursoOp)) {
			log.info("El id de curso ingresado no existe.");
		}
		Curso curso = cursoOp.get();
		
		inscripcion.setAlumno(alumno);
		inscripcion.setCurso(curso);
		inscripcion.setFinalizado(inscripcionBform.isFinalizado());
		inscripcion.setConBeca(inscripcionBform.isConBeca());
		inscripcion.setPorcentBeca(inscripcionBform.getPorcentBeca());
		
		return inscripcion;
	 
	}
	

}
