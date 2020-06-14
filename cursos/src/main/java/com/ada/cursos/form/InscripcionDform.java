package com.ada.cursos.form;

public class InscripcionDform {
	
	private Long id;
	private String nombre;
	private String apellido;
	private String fechaNac;
	private String genero;
	private String dir;	
	private boolean estudia;
	private boolean trabaja;
	private boolean ingresos;
	private float cantIngresos;
	private boolean familia;
	private int cantFamiliares;
	private Long cursoId;
	private boolean finalizado;
	
	
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
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
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;

	}
	public Long getCursoId() {
		return cursoId;
	}
	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}
	

}
