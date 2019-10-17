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


@Entity
@Table(name="reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_reserva;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_cliente"),
    name = "id_cliente", referencedColumnName = "id_cliente")
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_servicio"),
    name = "id_servicio", referencedColumnName = "id_servicio")
	private Servicio servicio ;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_reserva"),
    name = "id_estado_reserva", referencedColumnName = "id_estado_reserva")
	private EstadoReserva estado_reserva;


	public Long getId_reserva() {
		return id_reserva;
	}


	public void setId_reserva(Long id_reserva) {
		this.id_reserva = id_reserva;
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
