package com.app.myproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.myproject.model.Role;

public interface RoleService {
	Page<Role> getRoles(Pageable pageable);

	Role addRole(Role role);

	Role getRoleById(Long id);
}
