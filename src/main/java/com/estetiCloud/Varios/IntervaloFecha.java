package com.estetiCloud.Varios;

import javax.validation.constraints.NotNull;

public class IntervaloFecha {
	
	@NotNull
	private String fechaInicio;
	
	@NotNull
	private String fechaFin;
	
	public IntervaloFecha() {
		
	};
	public IntervaloFecha(String fechaInicio, String fechaFin) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public String toString() {
		return "IntervaloFecha [fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
	

}
