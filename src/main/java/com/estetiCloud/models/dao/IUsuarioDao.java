package com.estetiCloud.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.estetiCloud.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	
	@Query("select u from Usuario u where u.username=?1")
    public Usuario findByUsername(String username);
	
	@Modifying
    @Query(value = "insert into usuario_roles (usuario_id,role_id) VALUES (:usuario_id,:role_id)", nativeQuery = true)
    @Transactional
    void saveUsuario_Roles(@Param("usuario_id") Long usuario_id, @Param("role_id") Long id);
}
