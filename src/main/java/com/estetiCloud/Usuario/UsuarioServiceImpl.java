package com.estetiCloud.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if(usuario == null){
            logger.error("Error en el login: no existe usuario: "+ username+ "en el sistema");
            throw new UsernameNotFoundException("Error en el login: no existe usuario: "+ username+ "en el sistema");

        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: "+authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(),usuario.getPassword(), usuario.isEnable(), true,
                true, true, authorities );
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
    @Override
    @Transactional
    public void save(Usuario us) {
        usuarioDao.save(us);
    }

    @Override
    @Transactional
    public void saveUsuario_Roles(Long id_User, Long id_Rol) {
        usuarioDao.saveUsuario_Roles(id_User, id_Rol);

    }
}
