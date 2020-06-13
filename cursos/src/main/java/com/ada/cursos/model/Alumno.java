package com.ada.cursos.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

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
	
	@Embedded
	private DatosSE datosSE;
	
	

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

