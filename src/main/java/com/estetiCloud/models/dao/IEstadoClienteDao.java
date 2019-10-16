package com.estetiCloud.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.estado_cliente;

public interface IEstadoClienteDao extends JpaRepository<estado_cliente, Long>{

	

}
