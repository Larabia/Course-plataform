package com.ada.cursos.service;

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

	public Alumno generarAlumnoDeForm(AlumnoForm alumnoForm) {

		Alumno alumno = new Alumno();

		java.util.Optional<Usuario> usuario = usuarioRepo.findById(alumnoForm.getId());
		Usuario usuarioRespuesta = usuario.get();

		DatosSE datosSE = new DatosSE();
		datosSE.setEstudia(alumnoForm.isEstudia());
		datosSE.setTrabaja(alumnoForm.isTrabaja());

		alumno.setUsuario(usuarioRespuesta);
		alumno.setNombre(alumnoForm.getNombre());
		alumno.setApellido(alumnoForm.getApellido());
		alumno.setDatosSE(datosSE);

		return alumno;

	}
}
