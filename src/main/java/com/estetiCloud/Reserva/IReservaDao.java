package com.estetiCloud.Reserva;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estetiCloud.Cliente.Cliente;

public interface IReservaDao extends JpaRepository<Reserva, Long>{

	List<Reserva> findByCliente(Cliente cliente);

	

}
