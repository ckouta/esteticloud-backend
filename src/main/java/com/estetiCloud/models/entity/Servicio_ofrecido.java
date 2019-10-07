package com.estetiCloud.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="servicio_ofrecido")
public class Servicio_ofrecido {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_servicioOfrecido;
	
	@ManyToOne
	private Servicio servicio ;
	
	@ManyToOne
	private Profesional profesional ;
}
