package com.ada.cursos.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import com.google.common.collect.Lists;

@Service
public class InscripcionService {

	@Autowired
	UsuarioRepository usuarioRepo;

	@Autowired
	AlumnoRepository alumnoRepo;

	@Autowired
	AlumnoService alumnoServ;

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

	public Inscripcion guardar(Inscripcion inscripcion) {

		inscripRepo.save(inscripcion);

		return inscripcion;
	}

	public void borrar(Inscripcion inscripcion) {

		inscripRepo.delete(inscripcion);
		log.info("Empresa borrada.");
	}

	public List<Inscripcion> listar() {

		Iterable<Inscripcion> ListInscIt = inscripRepo.findAll();
		List<Inscripcion> listadoInscripcion = Lists.newArrayList(ListInscIt);

		return listadoInscripcion;
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
		inscripcion.setConBeca(inscripcionForm.isConBeca());

		return inscripcion;

	}

	public void actualizarCupo(Inscripcion inscripcion) {

		Curso curso = inscripcion.getCurso();
		int cupo = curso.getCupo();
		int cupoBecas = curso.getCupoBecas();

		if (!inscripcion.isConBeca()) {

			if (cupo == 0) {

				log.info("No quedan cupos disponibles en este curso.");
			} else {

				curso.setCupo(cupo - 1);
				cursoRepo.save(curso);
			}

		} else if (inscripcion.isBecaAprobada()) {

			if (cupoBecas == 0 || cupo == 0) {

				log.info("No quedan becas disponibles en este curso.");

			} else {

				if (tieneBecasEnProgreso(inscripcion)) {

					log.info("El usuario ya tiene becas en progreso.");
				} else {

					curso.setCupoBecas(cupoBecas - 1);
					curso.setCupo(cupo - 1);
					cursoRepo.save(curso);
				}
			}

		}

	}

	public boolean hayCuposDisponibles(Inscripcion inscripcion) {

		Curso curso = inscripcion.getCurso();
		int cupo = curso.getCupo();

		if (cupo == 0) {

			log.info("No quedan cupos disponibles en este curso.");
			return false;

		} else {

			return true;
		}

	}

	public boolean hayCuposDisponiblesConBeca(Inscripcion inscripcion) {

		Curso curso = inscripcion.getCurso();
		int cupo = curso.getCupo();
		int cupoBecas = curso.getCupoBecas();

		if (cupoBecas == 0 || cupo == 0) {
			log.info("No quedan becas disponibles en este curso.");
			return false;

		} else {
			return true;

		}

	}

	public boolean tieneBecasEnProgreso(Inscripcion inscripcion) {

		Alumno alumno = inscripcion.getAlumno();

		Iterable<Inscripcion> listInscIt = inscripRepo.findByAlumno(alumno);
		List<Inscripcion> inscripciones = Lists.newArrayList(listInscIt);
		List<Inscripcion> InscBecaEnProgreso = new ArrayList<Inscripcion>();
		Iterator<Inscripcion> filtrarPorFinalizadoYbeca = inscripciones.iterator();

		while (filtrarPorFinalizadoYbeca.hasNext()) {

			Inscripcion inscripcionIt = filtrarPorFinalizadoYbeca.next();

			if (!inscripcionIt.isFinalizado() && inscripcionIt.isBecaAprobada()) {
				InscBecaEnProgreso.add(inscripcionIt);
			}
		}

		if (InscBecaEnProgreso.isEmpty()) {
			return false;
		} else {
			log.info("El usuario tiene otras becas en progreso");
			return true;
		}

	}

	public boolean tieneDatosSE(Inscripcion inscripcion) {

		Alumno alumno = inscripcion.getAlumno();

		if (alumno.getDatosSE() == null) {
			log.info("El alumno no tiene cargados los datos SE.");
			return false;
		} else {
			return true;
		}
	}

	public boolean cumpleRequisitos(Inscripcion inscripcion) {

		if (!inscripcion.isConBeca()) {

			if (hayCuposDisponibles(inscripcion)) {
				return true;
			} else {
				return false;
			}
		} else if (inscripcion.isConBeca()) {
			if (hayCuposDisponiblesConBeca(inscripcion) && !tieneBecasEnProgreso(inscripcion)
					&& tieneDatosSE(inscripcion)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
