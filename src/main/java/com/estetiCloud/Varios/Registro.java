package com.estetiCloud.Varios;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.estetiCloud.Cliente.Cliente;
import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Usuario.Usuario;

public class Registro {

	private Usuario  usuario;
	
	private Profesional profesional;
	
	private Cliente cliente;
	
	public Registro() {
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
	
	
	
}
