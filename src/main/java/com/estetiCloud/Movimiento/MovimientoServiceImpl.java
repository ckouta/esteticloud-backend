package com.estetiCloud.Movimiento;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServiceImpl implements IMovimientoService {
	@Autowired
	private IMovimientoDao movimientoDao;
	
	@Transactional(readOnly=true)
	public List<Movimiento> findAll() {
		return movimientoDao.findAll();
	}

	@Transactional
	public void save(Movimiento movimiento) {
		movimientoDao.save(movimiento);
	}

	@Transactional(readOnly=true)
	public Movimiento findOne(Long id) {
		return movimientoDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		movimientoDao.deleteById(id);
	}
}
