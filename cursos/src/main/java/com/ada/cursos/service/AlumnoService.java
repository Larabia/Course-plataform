package com.ada.cursos.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.cursos.form.AlumnoForm;
import com.ada.cursos.model.Alumno;
import com.ada.cursos.model.DatosSE;
import com.ada.cursos.model.Usuario;
import com.ada.cursos.repository.UsuarioRepository;

@Service
public class AlumnoService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private DatosSEUtil datosSEUtil;

	public Alumno altaAlumno(AlumnoForm alumnoForm) {

		Alumno alumno = new Alumno();

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