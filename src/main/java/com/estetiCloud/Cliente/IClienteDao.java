package com.estetiCloud.Cliente;


import org.springframework.data.jpa.repository.JpaRepository;

import com.estetiCloud.Profesional.Profesional;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

	Cliente findByEmail(String email);

	

}
