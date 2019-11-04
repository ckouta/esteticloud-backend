package com.estetiCloud.models.service;

import com.estetiCloud.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
