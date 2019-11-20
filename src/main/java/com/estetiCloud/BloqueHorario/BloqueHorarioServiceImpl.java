package com.estetiCloud.BloqueHorario;

import java.time.LocalTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetiCloud.Varios.RangoHora;

@Service
public class BloqueHorarioServiceImpl implements IBloqueHorarioService {
	@Autowired
	private IBloqueHorarioDao bloqueDao;

	@Autowired
	private IEstadoBloqueDao estadoDao;

	@Autowired
	private IBloqueHorarioService bloqueService;

	@Transactional(readOnly = true)
	public List<Bloque_horario> findAll() {
		return bloqueDao.findAll();
	}

	@Transactional
	public void save(Bloque_horario cliente) {
		bloqueDao.save(cliente);
	}

	@Transactional(readOnly = true)
	public Bloque_horario findOne(Long id) {
		return bloqueDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		bloqueDao.deleteById(id);
	}

	@Override
	public void generarBloques(RangoHora rango) {
		LocalTime inicio=LocalTime.parse(rango.getHoraInicio());
		LocalTime finInterv;		
		LocalTime fin=LocalTime.parse(rango.getHoraFin());

		while(inicio.compareTo(fin)< 0) {
				
			finInterv=inicio.plusMinutes(10);
			for (int i = 0; i < 7; i++) {
		
				Bloque_horario bloque = new Bloque_horario(null,inicio.toString(),finInterv.toString(),i+"",estadoDao.getOne((long) 1));
				bloqueService.save(bloque);
			}				
			inicio=finInterv;				
		}
	}

	@Override
	public List<Bloque_horario> findByDiaSemana(String dia_semana) {

		return bloqueDao.findAllByDiaSemana(dia_semana);
	}

	@Override
	public List<Bloque_horario> findByDiaSemanaAndHoraInicioBetween(String diaSemana, String horaInicio, String horaFin) {

		return bloqueDao.findByDiaSemanaAndHoraInicioBetween(diaSemana, horaInicio,horaFin);
	}
	
	
}
