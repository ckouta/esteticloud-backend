package com.estetiCloud.Reserva;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoReservaServiceImpl implements IEstadoReservaService {
	@Autowired
	private IEstadoReservaDao estadoReservaDao;
	
	@Transactional(readOnly=true)
	public List<EstadoReserva> findAll() {
		return estadoReservaDao.findAll();
	}

	@Transactional
	public void save(EstadoReserva reserva) {
		estadoReservaDao.save(reserva);
	}

	@Transactional(readOnly=true)
	public EstadoReserva findOne(Long id) {
		return estadoReservaDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		estadoReservaDao.deleteById(id);
	}
}
