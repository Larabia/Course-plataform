package com.ada.cursos.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;

@Entity
@Table (name = "Alumno")

public class Alumno {
	
	@Id
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn (name = "alumno_id")
    private Usuario usuario;		
	private String nombre;	
	private String apellido;
	private Date fechaNac;
	private String genero;
	private String dir;	
	@Embedded 
	@Column(nullable = true)
	private DatosSE datosSE;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="alumno")
    @JsonManagedReference
    private List <Inscripcion> inscripciones;
	

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public DatosSE getDatosSE() {
		return datosSE;
	}

	public void setDatosSE(DatosSE datosSE) {
		this.datosSE = datosSE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}	
	
}

