package com.ada.cursos.service;

import org.springframework.stereotype.Service;

import com.ada.cursos.form.AlumnoForm;
import com.ada.cursos.model.DatosSE;

@Service
public class DatosSEUtil {
	
	public DatosSE nuevoDatosSE(AlumnoForm alumnoForm ) {
		
		DatosSE datosSE = new DatosSE();
		datosSE.setEstudia(alumnoForm.isEstudia());
		datosSE.setTrabaja(alumnoForm.isTrabaja());
		datosSE.setIngresos(alumnoForm.isIngresos());
		datosSE.setCantIngresos(alumnoForm.getCantIngresos());
		datosSE.setFamilia(alumnoForm.isFamilia());
		datosSE.setCantFamiliares(alumnoForm.getCantFamiliares());
		
		return datosSE;
	}

}
