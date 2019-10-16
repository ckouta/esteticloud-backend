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

	@NotEmpty
	private Long idBloque;

	@NotEmpty
	private String horaInicio;

	@NotEmpty
	private String horaFin;

	@NotEmpty
	private String dia_semana;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_bloque"),
    name = "id_estado_bloque", referencedColumnName = "id_estado_bloque")
	private estado_bloque estado_bloque;
	
	

	public Bloque_horario(@NotEmpty Long idBloque, @NotEmpty String horaInicio, @NotEmpty String horaFin,
			@NotEmpty String dia_semana, com.estetiCloud.models.entity.estado_bloque estado_bloque) {
		super();
		this.idBloque = idBloque;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.dia_semana = dia_semana;
		this.estado_bloque = estado_bloque;
	}

	public Bloque_horario() {
		
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

	public String getDia_semana() {
		return dia_semana;
	}

	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public estado_bloque getEstado_bloque() {
		return estado_bloque;
	}

	public void setEstado_bloque(estado_bloque estado_bloque) {
		this.estado_bloque = estado_bloque;
	}
	
	
	
}
