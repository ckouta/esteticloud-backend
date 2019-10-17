package com.estetiCloud.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.estetiCloud.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	

}
