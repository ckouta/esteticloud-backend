package com.estetiCloud.Servicio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="servicio")
@SQLDelete(sql="UPADTE servicio SET id_estado_servicio = 2 where id_servicio=?")
@Where(clause="id_estado_servicio != 2")
public class Servicio implements Serializable{
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_servicio;
	@NotEmpty
	private String nombre;
	@NotNull
	private Integer duracion;
	@NotNull
	private Integer precio;
	@NotNull
	private String descripcion;
	
	
	private String foto;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "id_estado_servicio"),
    name = "id_estado_servicio", referencedColumnName = "id_estado_servicio")
	private EstadoServicio estado_servicio ;

	
	public Servicio() {
		
	}
	


	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Long getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(Long id_servicio) {
		this.id_servicio = id_servicio;
	}

	public EstadoServicio getEstado_servicio() {
		return estado_servicio;
	}

	public void setEstado_servicio(EstadoServicio estado_servicio) {
		this.estado_servicio = estado_servicio;
	}
	
}
