package com.estetiCloud.models.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="profesional")
public class Profesional {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_profesional;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	@NotEmpty
	private String telefono;

	@NotEmpty
	@Email
	private String email;
	

	
	public Profesional() {
		
	}

	public Long getId() {
		return id_profesional;
	}

	public void setId(Long id) {
		this.id_profesional = id;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Profesional [id=" + id_profesional + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", email=" + email + "]";
	}
	
	
}
