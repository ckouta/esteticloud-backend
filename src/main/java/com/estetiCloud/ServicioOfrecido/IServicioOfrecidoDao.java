package com.estetiCloud.ServicioOfrecido;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Servicio.Servicio;

public interface IServicioOfrecidoDao extends JpaRepository<ServicioOfrecido, Long>{

	//Busca por Profesional
	public List<ServicioOfrecido> findByProfesional(Profesional profesional);
	
	//Busca por servicio
	public List<ServicioOfrecido> findByServicio(Servicio servicio);

}
