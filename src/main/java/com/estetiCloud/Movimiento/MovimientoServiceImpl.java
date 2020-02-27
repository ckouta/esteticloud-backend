package com.estetiCloud.Movimiento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.ServicioOfrecido.ServicioOfrecido;
import com.estetiCloud.Varios.IntervaloFecha;
import com.estetiCloud.Varios.RangoFecha;

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
	@Transactional(readOnly=true)
	public List<Movimiento> buscarPorProfesional(Profesional profesional) {
		// TODO Auto-generaMOvited method stub
		return movimientoDao.findByProfesionalOrderByFechaDesc(profesional);
	}

	@Transactional(readOnly=true)
	public List<Object> findFechaMovimiento(IntervaloFecha fecha) {
		// TODO Auto-generated method stub
		return movimientoDao.findFechaMovimientos(LocalDate.parse(fecha.getFechaInicio()), LocalDate.parse(fecha.getFechaFin()));
	}
}
