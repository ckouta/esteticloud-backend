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
import javax.validation.constraints.NotEmpty;

/**
 * @author ckout
 *
 */
@Entity
@Table(name = "bloque_horario")
public class Bloque_horario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBloque;

	@NotEmpty
	private String horaInicio;

	@NotEmpty
	private String horaFin;

	@NotEmpty
	private String diaSemana;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_bloque"),
    name = "id_estado_bloque", referencedColumnName = "id_estado_bloque")
	private EstadoBloque estado_bloque;
	
	public Bloque_horario() {}
	

	public Bloque_horario(Long idBloque, @NotEmpty String horaInicio, @NotEmpty String horaFin,
			@NotEmpty String dia_semana, EstadoBloque estado_bloque) {

		this.idBloque = idBloque;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.diaSemana = dia_semana;
		this.estado_bloque = estado_bloque;
	}

	public Long getIdBloque() {
		return idBloque;
	}

	public void setIdBloque(Long idBloque) {
		this.idBloque = idBloque;
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

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String dia_semana) {
		this.diaSemana = dia_semana;
	}

	public EstadoBloque getEstado_bloque() {
		return estado_bloque;
	}

	public void setEstado_bloque(EstadoBloque estado_bloque) {
		this.estado_bloque = estado_bloque;
	}

	@Override
	public String toString() {
		return "Bloque_horario [idBloque=" + idBloque + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin
				+ ", dia_semana=" + diaSemana + ", estado_bloque=" + estado_bloque + "]";
	}
	
	
	
}
