package com.ada.cursos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table (name = "Curso")
public class Curso {
	
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private Long id;
	private String nombre;
	private int horas;
	private String modalidad;
	private float precio;
	private String categoria;
	private int cupo;
	private int cupoBecas;
	
	@ManyToOne
    @JoinColumn(name="emp_id", nullable=false)
	@JsonBackReference
    private Empresa empresa;
	
	private boolean abierto;
	
	
    
	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public boolean isAbierto() {
		return abierto;
	}


	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}


	public String getModalidad() {
		return modalidad;
	}


	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public int getCupo() {
		return cupo;
	}


	public void setCupo(int cupo) {
		this.cupo = cupo;
	}


	public int getCupoBecas() {
		return cupoBecas;
	}


	public void setCupoBecas(int cupoBecas) {
		this.cupoBecas = cupoBecas;
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
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	

}
