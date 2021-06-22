package com.mea.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mea.api.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
