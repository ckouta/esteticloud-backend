package com.estetiCloud.Reserva;

import java.util.List;

import com.estetiCloud.Cliente.Cliente;

public interface IReservaService {

	List<Reserva> findAll();

	void save(Reserva reserva);

	Reserva findOne(Long id);

	void delete(Long id);
	
	List<Reserva> findOneCliente(Cliente cliente);
	

}
