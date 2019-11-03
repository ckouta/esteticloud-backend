package com.estetiCloud.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.Profesional;
import com.estetiCloud.models.entity.Servicio;
import com.estetiCloud.models.entity.Servicio_ofrecido;

public interface IServicioOfrecidoDao extends JpaRepository<Servicio_ofrecido, Long>{

	//Busca por Profesional
	public List<Servicio_ofrecido> findByProfesional(Profesional profesional);
	
	//Busca por servicio
	public List<Servicio_ofrecido> findByServicio(Servicio servicio);

}
