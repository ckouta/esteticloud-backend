package com.estetiCloud.Varios;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class RangoFecha {

	private Long id;
	
	@NotNull
	private String fecha;
	
	private String horaInicio;
	
	private String horaFin;
	
	public RangoFecha() {
		
	}

	public RangoFecha(Long id, String fecha, String horaInicio, String horaFin) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	@Override
	public String toString() {
		return "RangoFecha [id=" + id + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin
				+ "]";
	}
	
	
	
	
	
}
