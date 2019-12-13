package com.estetiCloud.Profesional;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfesionalDao extends JpaRepository<Profesional, Long>{

	Profesional findByEmail(String email);

	Profesional findByRut(String rut);

	

}
