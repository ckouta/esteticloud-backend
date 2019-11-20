package com.estetiCloud.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);

	void save(Usuario us);

	void saveUsuario_Roles(Long id_User, Long id_Rol);
}
