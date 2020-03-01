package com.estetiCloud.Reserva;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.estetiCloud.Cliente.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements IReservaService {
	@Autowired
	private IReservaDao reservaDao;
	
	@Transactional(readOnly=true)
	public List<Reserva> findAll() {
		return reservaDao.findAll();
	}

	@Transactional
	public void save(Reserva reserva) {
		reservaDao.save(reserva);
	}

	@Transactional(readOnly=true)
	public Reserva findOne(Long id) {
		return reservaDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		reservaDao.deleteById(id);
	}
	@Transactional(readOnly=true)
	public List<Reserva> findOneCliente(Cliente cliente) {
		return reservaDao.findByClienteOrderByIdReservaDesc(cliente);
	}
	@Transactional(readOnly=true)
	public List<Object> findServicioAnteriorCliente(Cliente cliente) {
		return reservaDao.findServicioAnteriores(cliente);
	}
}
