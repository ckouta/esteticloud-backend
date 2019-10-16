package com.estetiCloud.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.Movimiento;

public interface IMovimientoDao extends JpaRepository<Movimiento, Long>{

	

}
