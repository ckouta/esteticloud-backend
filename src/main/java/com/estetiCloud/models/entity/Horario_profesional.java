package com.estetiCloud.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
