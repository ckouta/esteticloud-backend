package com.estetiCloud.HorarioProfesional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.estetiCloud.BloqueHorario.Bloque_horario;
import com.estetiCloud.BloqueHorario.IBloqueHorarioService;
import com.estetiCloud.Profesional.IProfesionalService;
import com.estetiCloud.Profesional.Profesional;
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
		
		List<Bloque_horario> bloques = bloqueService.findByDiaSemanaAndHoraInicioBetween(dia+"",rango.getHoraInicio(),horaFin.minusMinutes(1).toString());
		
		for (Bloque_horario bloque_horario : bloques) {
			save(new HorarioProfesional(null, fechaInicio, prof, bloque_horario, null, estadoHorarioProfesional));
		}	
	}
	@Transactional(readOnly=true)
	public List<HorarioProfesional> findAllFecha(RangoFecha rango) {
		LocalDate fechaInicio = LocalDate.parse(rango.getFecha());
		/*List<Horario_profesional> horas = new ArrayList<Horario_profesional>();
		List<Horario_profesional> lista = horarioProfesionalDao.findAll();
		for(int i= 0; i< lista.size();i++) {
			if(lista.get(i).getFecha().equals(fechaInicio) && lista.get(i).getProfesional().getId_profesional()==rango.getId()) {
				System.out.println(lista.size() +" =="+ i);
				horas.add(lista.get(i));
				
			}
		}
		return horas;*/
		return horarioProfesionalDao.findByFechaAndProfesional(fechaInicio, profService.findOne(rango.getId()));
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

}