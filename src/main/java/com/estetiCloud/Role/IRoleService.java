package com.estetiCloud.Role;

import java.util.List;


public interface IRoleService {

	List<Role> findAll();

	Role findOne(Long id);
}
