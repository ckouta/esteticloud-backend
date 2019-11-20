package com.estetiCloud.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.estetiCloud.models.entity.Profesional;

public interface IProfesionalDao extends JpaRepository<Profesional, Long>{

	Profesional findByEmail(String email);

	

}
