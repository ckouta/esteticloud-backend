package com.estetiCloud.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.Reserva;

public interface IReservaDao extends JpaRepository<Reserva, Long>{

	

}
