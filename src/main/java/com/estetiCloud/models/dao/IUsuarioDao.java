package com.estetiCloud.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.estetiCloud.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	
	@Query("select u from Usuario u where u.username=?1")
    public Usuario findByUsername(String username);
}
