package com.estetiCloud.HorarioProfesional;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoHorarioProfesionalServiceImpl implements IEstadoHorarioProfesionalService {
	@Autowired
	private IEstadoHorarioProfesionalDao estadoBloqueDao;
	
	@Transactional(readOnly=true)
	public List<EstadoHorarioProfesional> findAll() {
		return estadoBloqueDao.findAll();
	}

	@Transactional
	public void save(EstadoHorarioProfesional estadoBloque) {
		estadoBloqueDao.save(estadoBloque);
	}

	@Transactional(readOnly=true)
	public EstadoHorarioProfesional findOne(Long id) {
		return estadoBloqueDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		estadoBloqueDao.deleteById(id);
	}
}
