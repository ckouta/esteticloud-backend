package com.estetiCloud.models.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_profesional"),
    name = "id_estado_profesional", referencedColumnName = "id_estado_profesional")
	private estado_profesional estado_profesional;

	
	public Profesional(Long id_profesional, @NotEmpty String nombre, @NotEmpty String apellido,
			@NotEmpty String telefono, @NotEmpty @Email String email,
			com.estetiCloud.models.entity.estado_profesional estado_profesional) {
		super();
		this.id_profesional = id_profesional;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.estado_profesional = estado_profesional;
	}

	public Profesional() {
		
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

	public Long getId_profesional() {
		return id_profesional;
	}

	public void setId_profesional(Long id_profesional) {
		this.id_profesional = id_profesional;
	}

	public estado_profesional getEstado_profesional() {
		return estado_profesional;
	}

	public void setEstado_profesional(estado_profesional estado_profesional) {
		this.estado_profesional = estado_profesional;
	}

	@Override
	public String toString() {
		return "Profesional [id=" + id_profesional + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", email=" + email + "]";
	}
	
	
}
