package com.ada.cursos.form;


public class InscripcionForm {
	
	//Tipo de inscripcion
	private boolean conBeca;
	private boolean becaAprobada;
	private String porcentBeca;
	
	//Datos del curso
	private Long cursoId;
	private boolean finalizado;
	
	//Datos del alumno
	private Long alumnoId;

	
	
	public boolean isBecaAprobada() {
		return becaAprobada;
	}

	public void setBecaAprobada(boolean becaAprobada) {
		this.becaAprobada = becaAprobada;
	}

	public boolean isConBeca() {
		return conBeca;
	}

	public void setConBeca(boolean conBeca) {
		this.conBeca = conBeca;
	}

	public String getPorcentBeca() {
		return porcentBeca;
	}

	public void setPorcentBeca(String porcentBeca) {
		this.porcentBeca = porcentBeca;
	}

	public Long getCursoId() {
		return cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Long getAlumnoId() {
		return alumnoId;
	}

	public void setAlumnoId(Long alumnoId) {
		this.alumnoId = alumnoId;
	}
	
	
	
}
