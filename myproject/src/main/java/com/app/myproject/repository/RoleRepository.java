package com.app.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.myproject.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
