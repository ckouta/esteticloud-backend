package com.estetiCloud.Reserva;

import java.util.List;

public interface IReservaService {

	List<Reserva> findAll();

	void save(Reserva reserva);

	Reserva findOne(Long id);

	void delete(Long id);

}
