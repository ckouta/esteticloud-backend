package com.estetiCloud.HorarioProfesional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.estetiCloud.BloqueHorario.BloqueHorario;
import com.estetiCloud.BloqueHorario.IBloqueHorarioService;
import com.estetiCloud.Profesional.IProfesionalService;
import com.estetiCloud.Profesional.Profesional;
import com.estetiCloud.Reserva.Reserva;
import com.estetiCloud.Varios.IntervaloFecha;
import com.estetiCloud.Varios.RangoFecha;

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
	public List<HorarioProfesional> findAll() {
		return horarioProfesionalDao.findAll();
	}

	@Transactional
	public void save(HorarioProfesional horarioProfesional) {
		horarioProfesionalDao.save(horarioProfesional);
	}

	@Transactional(readOnly=true)
	public HorarioProfesional findOne(Long id) {
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
		
		List<BloqueHorario> bloques = bloqueService.findByDiaSemanaAndHoraInicioBetween(dia+"",rango.getHoraInicio(),horaFin.minusMinutes(1).toString());
		
		for (BloqueHorario bloque_horario : bloques) {
			if(horarioProfesionalDao.findByFechaAndBloqueHorario(fechaInicio, bloque_horario) == null) {
				save(new HorarioProfesional(null, fechaInicio, prof, bloque_horario, null, estadoHorarioProfesional));
			}
		}	
	}
	@Transactional(readOnly=true)
	public List<HorarioProfesional> findAllFecha(RangoFecha rango) {
		LocalDate fechaInicio = LocalDate.parse(rango.getFecha());
		return horarioProfesionalDao.findByFechaAndProfesionalOrderByFecha(fechaInicio, profService.findOne(rango.getId()));
	}
	@Transactional(readOnly=true)
	public List<HorarioProfesional> findAllhoras(Profesional profesional) {
		List<HorarioProfesional> horario = new ArrayList<HorarioProfesional>();
		horarioProfesionalDao.findByProfesional(profesional).forEach(action ->{
			if(action.getReserva()!=null) {
				horario.add(action);
			}
		});
		
		Collections.sort(horario, new Comparator<HorarioProfesional>() {

			@Override
			public int compare(HorarioProfesional h1, HorarioProfesional h2) {
				
				return new Integer(h1.getReserva().getId_reserva().intValue()).compareTo(new Integer(h2.getReserva().getId_reserva().intValue())); 
			}

		});
		return horario;
	}
	
	@Transactional(readOnly=true)
	public HorarioProfesional findByReserva(Reserva reserva) {
		List<HorarioProfesional> lista = horarioProfesionalDao.findByReserva(reserva);
		return lista.get(0);
	}
	
	@Transactional(readOnly=true)
	public List<Object> findTopServicios (IntervaloFecha fecha){
		
		return horarioProfesionalDao.findTopServicios(LocalDate.parse(fecha.getFechaInicio()),LocalDate.parse(fecha.getFechaFin()));
	}
	
	@Transactional(readOnly=true)
	public List<Object> findTopReservas (IntervaloFecha fecha){
		
		return horarioProfesionalDao.findTopReservas(LocalDate.parse(fecha.getFechaInicio()),LocalDate.parse(fecha.getFechaFin()));
	}
	
	@Transactional(readOnly=true)
	public List<Object> findTopClientes (IntervaloFecha fecha){
	return horarioProfesionalDao.findTopClientes(LocalDate.parse(fecha.getFechaInicio()),LocalDate.parse(fecha.getFechaFin()));
	}
	@Transactional(readOnly=true)
	public List<Object> findTopProfesional (IntervaloFecha fecha){
	return horarioProfesionalDao.findTopProfesional(LocalDate.parse(fecha.getFechaInicio()),LocalDate.parse(fecha.getFechaFin()));
	}

}
