package com.estetiCloud.HorarioProfesional;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.estetiCloud.BloqueHorario.BloqueHorario;
import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Reserva.Reserva;



@Entity
@Table(name = "horario_profesional")
public class HorarioProfesional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_horarioProfesional;
	
	@NotNull
	private LocalDate fecha;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(cascade = CascadeType.MERGE)
	private Profesional profesional ;
	@ManyToOne(cascade = CascadeType.MERGE)
	private BloqueHorario bloqueHorario ;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_reserva"),
    name = "id_reserva", referencedColumnName = "id_reserva")
	private Reserva reserva;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_horarioProfesional"),
    name = "id_estado_horarioProfesional", referencedColumnName = "id_estado_horarioProfesional")
	private EstadoHorarioProfesional estado_horarioProfesional;
	
	
	public HorarioProfesional(Long id_horarioProfesional, @NotEmpty LocalDate fecha, Profesional profesional,
			BloqueHorario bloque_horario, Reserva reserva, EstadoHorarioProfesional estado_horarioProfesional) {
		super();
		this.id_horarioProfesional = id_horarioProfesional;
		this.fecha = fecha;
		this.profesional = profesional;
		this.bloqueHorario = bloque_horario;
		this.reserva = reserva;
		this.estado_horarioProfesional = estado_horarioProfesional;
	}
	
	public HorarioProfesional() {
		
	}
	public Long getId_horarioProfesional() {
		return id_horarioProfesional;
	}
	public void setId_horarioProfesional(Long id_horarioProfesional) {
		this.id_horarioProfesional = id_horarioProfesional;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Profesional getProfesional() {
		return profesional;
	}
	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
	public BloqueHorario getBloque_horario() {
		return bloqueHorario;
	}
	public void setBloque_horario(BloqueHorario bloque_horario) {
		this.bloqueHorario = bloque_horario;
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
