package com.ada.cursos.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

//esta clase representa los datos socio-economicos del alumno

@Embeddable
public class DatosSE {
	
	private boolean estudia;
	private boolean trabaja;
	
	public boolean isEstudia() {
		return estudia;
	}
	public void setEstudia(boolean estudia) {
		this.estudia = estudia;
	}
	public boolean isTrabaja() {
		return trabaja;
	}
	public void setTrabaja(boolean trabaja) {
		this.trabaja = trabaja;
	}
	
	

}
