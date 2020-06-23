package com.ada.cursos.form;


public class InscripcionForm {
	
	//Tipo de inscripcion
	private boolean conBeca;
	
	//Datos del curso
	private Long cursoId;
	
	//Datos del alumno
	private Long alumnoId;

	
	
	public boolean isConBeca() {
		return conBeca;
	}

	public void setConBeca(boolean conBeca) {
		this.conBeca = conBeca;
	}


	public Long getCursoId() {
		return cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}


	public Long getAlumnoId() {
		return alumnoId;
	}

	public void setAlumnoId(Long alumnoId) {
		this.alumnoId = alumnoId;
	}
	
	
	
}
