package com.estetiCloud.models.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.estetiCloud.models.dao.IHorarioProfesionalDao;
import com.estetiCloud.models.entity.Bloque_horario;
import com.estetiCloud.models.entity.EstadoHorarioProfesional;
import com.estetiCloud.models.entity.Horario_profesional;
import com.estetiCloud.models.entity.Profesional;
import com.estetiCloud.models.entity.RangoFecha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class HorarioProfesionalServiceImpl implements IHorarioProfesionalService {
	@Autowired
	private IHorarioProfesionalDao horarioProfesionalDao;
	
	@Autowired
	private IProfesionalService profService;
	
	@Autowired
	private IBloqueHorarioService bloqueService;
	
	@Autowired
	private IEstadoHorarioProfesionalService estadoHorarioProfService;
	
	@Transactional(readOnly=true)
	public List<Horario_profesional> findAll() {
		return horarioProfesionalDao.findAll();
	}

	@Transactional
	public void save(Horario_profesional horarioProfesional) {
		horarioProfesionalDao.save(horarioProfesional);
	}

	@Transactional(readOnly=true)
	public Horario_profesional findOne(Long id) {
		return horarioProfesionalDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		horarioProfesionalDao.deleteById(id);
	}

	@Override
	public void saveRango(RangoFecha rango) {
		LocalDate fechaInicio = LocalDate.parse(rango.getFecha());
		LocalTime horaFin = LocalTime.parse(rango.getHoraFin());
		Long dia = (long) ((fechaInicio.getDayOfWeek().ordinal()+1)%7);
		Profesional prof = profService.findOne(rango.getId());
		EstadoHorarioProfesional estadoHorarioProfesional = estadoHorarioProfService.findOne((long) 1);
		//es +1 porque se considerará el domingo=0, lunes=1 ...
		
		List<Bloque_horario> bloques = bloqueService.findByDiaSemanaAndHoraInicioBetween(dia+"",rango.getHoraInicio(),horaFin.minusMinutes(1).toString());
		
		for (Bloque_horario bloque_horario : bloques) {
			save(new Horario_profesional(null, fechaInicio, prof, bloque_horario, null, estadoHorarioProfesional));
		}
		
		
		
		
		
	}
}