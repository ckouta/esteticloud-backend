package com.estetiCloud.models.service;

import java.util.List;

import com.estetiCloud.models.entity.Cliente;

public interface IClienteService {

	List<Cliente> findAll();

	void save(Cliente profesional);

	Cliente findOne(Long id);

	void delete(Long id);

}
