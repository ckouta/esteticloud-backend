package com.estetiCloud.models.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	@NotEmpty
	private String telefono;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String Rut;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_cliente"),
    name = "id_estado_cliente", referencedColumnName = "id_estado_cliente")
	private EstadoCliente estado_cliente;
	

	public Cliente(Long id_cliente, @NotEmpty String nombre, @NotEmpty String apellido, @NotEmpty String telefono,
			@NotEmpty @Email String email, @NotEmpty String rut, EstadoCliente estado_cliente) {
		super();
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		Rut = rut;
		this.estado_cliente = estado_cliente;
	}

	public String getRut() {
		return Rut;
	}

	public void setRut(String rut) {
		Rut = rut;
	}

	public Cliente() {

	}

	public Long getId() {
		return id_cliente;
	}

	public void setId(Long id) {
		this.id_cliente = id;
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

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}


	public EstadoCliente getEstado_cliente() {
		return estado_cliente;
	}

	public void setEstado_cliente(EstadoCliente estado_cliente) {
		this.estado_cliente = estado_cliente;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}

}
