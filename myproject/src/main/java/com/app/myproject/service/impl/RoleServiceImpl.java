package com.app.myproject.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.myproject.model.Role;
import com.app.myproject.repository.RoleRepository;
import com.app.myproject.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Inject
	private RoleRepository roleRepository;
	
	@Override
	public Page<Role> getRoles(Pageable pageable){
		PageRequest request = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
		return roleRepository.findAll(request);
	}
	
	public Role addRole(Role role){
		return roleRepository.save(role);
	}

	@Override
	public Role getRoleById(Long id) {
		return roleRepository.findById(id).get();
	}
}
