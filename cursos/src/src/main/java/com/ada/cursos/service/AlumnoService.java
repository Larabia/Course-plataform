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

	public Alumno generarAlumnoDeForm(AlumnoForm alumnoForm) {

		Alumno alumno = new Alumno();

		java.util.Optional<Usuario> usuario = usuarioRepo.findById(alumnoForm.getId());
		Usuario usuarioRespuesta = usuario.get();

		DatosSE datosSE = new DatosSE();
		datosSE.setEstudia(alumnoForm.isEstudia());
		datosSE.setTrabaja(alumnoForm.isTrabaja());
		datosSE.setIngresos(alumnoForm.isIngresos());
		datosSE.setCantIngresos(alumnoForm.getCantIngresos());
		datosSE.setFamilia(alumnoForm.isFamilia());
		datosSE.setCantFamiliares(alumnoForm.getCantFamiliares());
		
		
		try {
			Date fechaNac = DateUtil.formatParse(dateUtil.PATTERN_D2_M2_Y4, alumnoForm.getFechaNac());
			alumno.setFechaNac(fechaNac);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		alumno.setUsuario(usuarioRespuesta);
		alumno.setNombre(alumnoForm.getNombre());
		alumno.setApellido(alumnoForm.getApellido());		
		alumno.setGenero(alumnoForm.getGenero());
		alumno.setDir(alumnoForm.getDir());
		alumno.setDatosSE(datosSE);

		return alumno;

	}
}
