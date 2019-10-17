package com.estetiCloud.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.EstadoCliente;

public interface IEstadoClienteDao extends JpaRepository<EstadoCliente, Long>{

	

}
