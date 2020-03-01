package com.estetiCloud.Reserva;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estetiCloud.Cliente.Cliente;

public interface IReservaDao extends JpaRepository<Reserva, Long>{

	List<Reserva> findByClienteOrderByIdReservaDesc(Cliente cliente);

	@Query("SELECT Distinct h.servicio, h.idReserva from Reserva h where (h.cliente = ?1) order by h.idReserva DESC")
	List<Object> findServicioAnteriores(Cliente cliente);
	

}
