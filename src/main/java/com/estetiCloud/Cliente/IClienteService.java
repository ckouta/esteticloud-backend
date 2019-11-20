package com.estetiCloud.Cliente;

import java.util.List;

import com.estetiCloud.Profesional.Profesional;

public interface IClienteService {

	List<Cliente> findAll();

	void save(Cliente profesional);

	Cliente findOne(Long id);

	void delete(Long id);

	Cliente findOneCorreo(String email);

}
