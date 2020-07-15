package com.ada.cursos.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Inscripcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//Tipo de inscripcion
	private boolean conBeca;
	private boolean becaAprobada;
	private String porcentBeca;
	
	//Datos del curso	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso_id")
	@JsonManagedReference
	private Curso curso;	
	private boolean finalizado;
	
	//Datos del alumno
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alumno_id")
	@JsonManagedReference
    private Alumno alumno;

	
	
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

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}




}
