package com.estetiCloud.Role;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao roleDao;
	
	@Transactional(readOnly=true)
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role findOne(Long id) {
		// TODO Auto-generated method stub
		return roleDao.findById(id).orElse(null);
	}

}
