package com.estetiCloud.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
}
