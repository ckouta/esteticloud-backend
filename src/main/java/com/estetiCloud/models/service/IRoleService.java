package com.estetiCloud.models.service;

import java.util.List;


import com.estetiCloud.models.entity.Role;


public interface IRoleService {

	List<Role> findAll();

	Role findOne(Long id);
}
