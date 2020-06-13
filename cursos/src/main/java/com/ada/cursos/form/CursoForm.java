package com.ada.cursos.form;

public class CursoForm {
	
	private Long id;
	private String nombre;
	private int horas;
	private Long EmpresaId;
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
	public Long getEmpresaId() {
		return EmpresaId;
	}
	public void setEmpresaId(Long empresaId) {
		EmpresaId = empresaId;
	}

	

}
