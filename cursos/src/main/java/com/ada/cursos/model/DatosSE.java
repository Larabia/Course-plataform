package com.ada.cursos.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

//esta clase representa los datos socio-economicos del alumno

@Embeddable
public class DatosSE {
	
	private boolean estudia;
	private boolean trabaja;
	private boolean ingresos;
	private float cantIngresos;
	private boolean familia;
	private int cantFamiliares;
	
	
	public boolean isIngresos() {
		return ingresos;
	}
	public void setIngresos(boolean ingresos) {
		this.ingresos = ingresos;
	}
	public float getCantIngresos() {
		return cantIngresos;
	}
	public void setCantIngresos(float cantIngresos) {
		this.cantIngresos = cantIngresos;
	}
	public boolean isFamilia() {
		return familia;
	}
	public void setFamilia(boolean familia) {
		this.familia = familia;
	}
	public int getCantFamiliares() {
		return cantFamiliares;
	}
	public void setCantFamiliares(int cantFamiliares) {
		this.cantFamiliares = cantFamiliares;
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
