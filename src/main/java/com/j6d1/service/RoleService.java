package com.j6d1.service;

import java.util.List;
import java.util.Optional;

import com.j6d1.entity.Role;

public interface RoleService {

	Optional<Role> findById(String id);

	List<Role> findAll();

}
