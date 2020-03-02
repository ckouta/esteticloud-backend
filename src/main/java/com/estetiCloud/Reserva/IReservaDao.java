package com.estetiCloud.Reserva;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estetiCloud.Cliente.Cliente;

public interface IReservaDao extends JpaRepository<Reserva, Long>{

	List<Reserva> findByClienteOrderByIdReservaDesc(Cliente cliente);


}
