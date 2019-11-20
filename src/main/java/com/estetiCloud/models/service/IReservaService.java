package com.estetiCloud.models.service;

import java.util.List;


import com.estetiCloud.models.entity.Reserva;

public interface IReservaService {

	List<Reserva> findAll();

	void save(Reserva reserva);

	Reserva findOne(Long id);

	void delete(Long id);

}
