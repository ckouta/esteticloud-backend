package com.estetiCloud.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.Cliente;
import com.estetiCloud.models.entity.Profesional;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

	Cliente findByEmail(String email);

	

}
