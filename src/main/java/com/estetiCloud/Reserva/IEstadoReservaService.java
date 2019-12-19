package com.estetiCloud.Reserva;

import java.util.List;


public interface IEstadoReservaService {

	List<EstadoReserva> findAll();

	void save(EstadoReserva reserva);

	EstadoReserva findOne(Long id);

	void delete(Long id);
	

}
