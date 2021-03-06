package com.estetiCloud.Cliente;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService {
	@Autowired
	private IClienteDao clienteDao;
	
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}

	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Transactional(readOnly=true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	public Cliente findOneCorreo(String email) {
		
		return clienteDao.findByEmail(email);
	}
}
