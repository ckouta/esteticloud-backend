package com.estetiCloud.models.service;

import com.estetiCloud.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);

	void save(Usuario us);

	void saveUsuario_Roles(Long id_User, Long id_Rol);
}
