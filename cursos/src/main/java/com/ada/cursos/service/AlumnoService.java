package com.ada.cursos.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.AlumnoForm;
import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.DatosSE;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.AlumnoRepository;
import com.ada.cursos.repository.CursoRepository;
import com.ada.cursos.repository.UsuarioRepository;

@Service
public class AlumnoService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private AlumnoRepository alumnoRepo;

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private DatosSEUtil datosSEUtil;
	
	Logger log = Logger.getLogger(CursoRepository.class.getName());
	
    public Alumno porId(Long id) {
		
		Optional<Alumno> alumnoOp = alumnoRepo.findById(id);
		
		if (Optional.empty().equals(alumnoOp)) {
			log.info("El id ingresado no existe.");
		}
		
		Alumno alumno = alumnoOp.get();
		return alumno;
	}

	public Alumno cargarDatosForm(AlumnoForm alumnoForm, Alumno alumno) {

		java.util.Optional<Usuario> usuarioOp = usuarioRepo.findById(alumnoForm.getId());
		Usuario usuario = usuarioOp.get();

		DatosSE datosSE = datosSEUtil.nuevoDatosSE(alumnoForm);

		try {
			Date fechaNac = DateUtil.formatParse(dateUtil.PATTERN_Y4_M2_D2, alumnoForm.getFechaNac());
			alumno.setFechaNac(fechaNac);
		} catch (ParseException e) {
			System.out.println("El formato de fecha ingresado es incorrecto.");
			e.printStackTrace();
		}

		alumno.setUsuario(usuario);
		alumno.setNombre(alumnoForm.getNombre());
		alumno.setApellido(alumnoForm.getApellido());
		alumno.setGenero(alumnoForm.getGenero());
		alumno.setDir(alumnoForm.getDir());
		alumno.setDatosSE(datosSE);

		return alumno;

	}

}