package com.ada.cursos.form;

public class CursoForm {
	
	private String nombre;
	private int horas;
	private String modalidad;
	private float precio;
	private String categoria;
	private int cupo;
	private int cupoBecas;
	private Long EmpId;
	
	
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
	public Long getEmpId() {
		return EmpId;
	}
	public void setEmpId(Long empId) {
		EmpId = empId;
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
