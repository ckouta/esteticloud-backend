package com.estetiCloud.models.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Role;

    @Column(unique = true, length = 20)
    private String nombre;

	public Long getId_Role() {
		return id_Role;
	}

	public void setId_Role(Long id_Role) {
		this.id_Role = id_Role;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
