package com.estetiCloud.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "estado_servicioOfrecido")
public class estado_servicioOfrecido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_estado_servicioOfrecido;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;

}
