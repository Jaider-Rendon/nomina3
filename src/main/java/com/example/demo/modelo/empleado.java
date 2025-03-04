package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleados")

public class empleado {
	@Id
	@Column(name="identificacion")
	private Long identificación;
	
	@Column(name= "nombre", length = 50,nullable=false)
	private String nombre; 
	
	@Column(name= "apellidos", length = 80,nullable=false)
	private String apellidos; 
	
	@Column(name= "email", length = 100, nullable=false, unique = true)
	private String email;
	
	


	public empleado() {
		
	}




	public empleado(Long identificación, String nombre, String apellidos, String email) {
		super();
		this.identificación = identificación;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}




	public Long getIdentificación() {
		return identificación;
	}




	public void setIdentificación(Long identificación) {
		this.identificación = identificación;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getApellidos() {
		return apellidos;
	}




	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
