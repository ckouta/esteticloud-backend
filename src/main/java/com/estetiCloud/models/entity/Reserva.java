package com.estetiCloud.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_reserva;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Servicio servicio ;
	
	@OneToOne
	private Horario_profesional horario_profesional;
}
