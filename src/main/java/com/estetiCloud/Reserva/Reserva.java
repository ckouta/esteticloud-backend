package com.estetiCloud.Reserva;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.estetiCloud.Cliente.Cliente;
import com.estetiCloud.Servicio.Servicio;


@Entity
@Table(name="reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_reserva")
	private Long idReserva;
	
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_cliente"),
    name = "id_cliente", referencedColumnName = "id_cliente")
	private Cliente cliente;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_servicio"),
    name = "id_servicio", referencedColumnName = "id_servicio")
	private Servicio servicio ;
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_reserva"),
    name = "id_estado_reserva", referencedColumnName = "id_estado_reserva")
	private EstadoReserva estado_reserva;


	public Reserva(Long id_reserva, Cliente cliente, Servicio servicio, EstadoReserva estado_reserva) {
		super();
		this.idReserva = id_reserva;
		this.cliente = cliente;
		this.servicio = servicio;
		this.estado_reserva = estado_reserva;
	}
	public Reserva() {
		
	}


	public Long getId_reserva() {
		return idReserva;
	}


	public void setId_reserva(Long id_reserva) {
		this.idReserva = id_reserva;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}


	public EstadoReserva getEstado_reserva() {
		return estado_reserva;
	}


	public void setEstado_reserva(EstadoReserva estado_reserva) {
		this.estado_reserva = estado_reserva;
	}
	
	
}
