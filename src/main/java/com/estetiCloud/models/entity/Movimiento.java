package com.estetiCloud.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "movimiento")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_movimiento;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;

	@NotEmpty
	private Integer valor;
	
	@NotEmpty
	private Date fecha;



}
