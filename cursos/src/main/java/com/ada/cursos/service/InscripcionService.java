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
	CursoService cursoServ;

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

	public Inscripcion cargarDatosForm(InscripcionForm inscripcionForm, Inscripcion inscripcion) {

		Optional<Alumno> alumnoOp = alumnoRepo.findById(inscripcionForm.getAlumnoId());
		if (Optional.empty().equals(alumnoOp)) {
			log.info("El id de alumno ingresado no existe.");
		}
		Alumno alumno = alumnoOp.get();

		Optional<Curso> cursoOp = cursoRepo.findById(inscripcionForm.getCursoId());
		if (Optional.empty().equals(cursoOp)) {
			log.info("El id de curso ingresado no existe.");
		}
		Curso curso = cursoOp.get();

		inscripcion.setAlumno(alumno);
		inscripcion.setCurso(curso);
		inscripcion.setFinalizado(inscripcionForm.isFinalizado());
		inscripcion.setConBeca(inscripcionForm.isConBeca());
		inscripcion.setBecaAprobada(inscripcionForm.isBecaAprobada());
		inscripcion.setPorcentBeca(inscripcionForm.getPorcentBeca());

		return inscripcion;

	}

	public void actualizarCupo(InscripcionForm inscripForm) {

		Curso curso = cursoServ.porId(inscripForm.getCursoId());
		int cupo = curso.getCupo();
		int cupoBecas = curso.getCupoBecas();

		if (!inscripForm.isConBeca()) {

			if (cupo == 0) {
				
				log.info("No quedan cupos disponibles en este curso.");
			} else {
				
				curso.setCupo(cupo - 1);
				cursoRepo.save(curso);
			}

		} else if (inscripForm.isBecaAprobada()) {
			
			if (cupoBecas == 0 || cupo == 0 ) {
				
				log.info("No quedan becas disponibles en este curso.");
				
			} else {
				
				curso.setCupoBecas(cupoBecas - 1);
				curso.setCupo(cupo - 1);
				cursoRepo.save(curso);
			}

		}

	}

}
