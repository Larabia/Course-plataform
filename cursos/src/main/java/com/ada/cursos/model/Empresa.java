package com.ada.cursos.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonAutoDetect (fieldVisibility = Visibility.ANY)
@Entity
@Table (name = "Empresa")
public class Empresa {
	
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	private String cuil;
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "rep_id")
    private Rep rep;
	
	@JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy="empresa")
    private Set<Curso> cursos;
	
	
	
	public Set<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}
	public Rep getRep() {
		return rep;
	}
	public void setRep(Rep rep) {
		this.rep = rep;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	
	
	

}
