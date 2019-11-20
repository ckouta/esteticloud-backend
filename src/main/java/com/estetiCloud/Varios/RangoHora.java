package com.estetiCloud.Varios;

public class RangoHora {
	
	private String horaInicio;
	private String horaFin;
	
	public RangoHora(String horaInicio, String horaFin) {
		super();
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
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
		return "RangoHora [horaInicio=" + horaInicio + ", horaFin=" + horaFin + "]";
	}
	
	
}