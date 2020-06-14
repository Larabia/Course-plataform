package com.ada.cursos.model;

import javax.persistence.Embeddable;


//esta clase representa los datos socio-economicos del alumno

@Embeddable
public class DatosSE {
	
	private boolean estudia;
	private boolean trabaja;
	private boolean ingresos;
	private String cantIngresos;
	private boolean familia;
	private String cantFamiliares;
	
	
	public String getCantIngresos() {
		return cantIngresos;
	}
	public void setCantIngresos(String cantIngresos) {
		this.cantIngresos = cantIngresos;
	}
	public String getCantFamiliares() {
		return cantFamiliares;
	}
	public void setCantFamiliares(String cantFamiliares) {
		this.cantFamiliares = cantFamiliares;
	}
	public boolean isIngresos() {
		return ingresos;
	}
	public void setIngresos(boolean ingresos) {
		this.ingresos = ingresos;
	}

	public boolean isFamilia() {
		return familia;
	}
	public void setFamilia(boolean familia) {
		this.familia = familia;
	}

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
