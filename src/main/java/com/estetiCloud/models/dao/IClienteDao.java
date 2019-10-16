package com.estetiCloud.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

	

}
