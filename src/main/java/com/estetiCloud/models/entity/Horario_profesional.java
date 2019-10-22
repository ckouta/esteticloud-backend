package com.estetiCloud.models.entity;

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
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name = "horario_profesional")
public class Horario_profesional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_horarioProfesional;
	
	@NotEmpty
	private Date fecha;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Profesional profesional ;
	@ManyToOne(cascade = CascadeType.ALL)
	private Bloque_horario bloque_horario ;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_reserva"),
    name = "id_reserva", referencedColumnName = "id_reserva")
	private Reserva reserva;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_horarioProfesional"),
    name = "id_estado_horarioProfesional", referencedColumnName = "id_estado_horarioProfesional")
	private EstadoHorarioProfesional estado_horarioProfesional;
	
	
	public Horario_profesional(Long id_horarioProfesional, @NotEmpty Date fecha, Profesional profesional,
			Bloque_horario bloque_horario, Reserva reserva, EstadoHorarioProfesional estado_horarioProfesional) {
		super();
		this.id_horarioProfesional = id_horarioProfesional;
		this.fecha = fecha;
		this.profesional = profesional;
		this.bloque_horario = bloque_horario;
		this.reserva = reserva;
		this.estado_horarioProfesional = estado_horarioProfesional;
	}
	
	public Horario_profesional() {
		
	}
	public Long getId_horarioProfesional() {
		return id_horarioProfesional;
	}
	public void setId_horarioProfesional(Long id_horarioProfesional) {
		this.id_horarioProfesional = id_horarioProfesional;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Profesional getProfesional() {
		return profesional;
	}
	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
	public Bloque_horario getBloque_horario() {
		return bloque_horario;
	}
	public void setBloque_horario(Bloque_horario bloque_horario) {
		this.bloque_horario = bloque_horario;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public EstadoHorarioProfesional getEstado_horarioProfesional() {
		return estado_horarioProfesional;
	}

	public void setEstado_horarioProfesional(EstadoHorarioProfesional estado_horarioProfesional) {
		this.estado_horarioProfesional = estado_horarioProfesional;
	}
	
	
	
}
