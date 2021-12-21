package com.j6d1.service;

import java.util.List;
import java.util.Optional;

import com.j6d1.entity.Authority;

public interface AuthorityService {

	Optional<Authority> findById(Integer id);

	List<Authority> findAll();

	void deleteById(Integer id);

	Authority save(Authority entity);

	List<Authority> findAuthoritiesOfAdmin();

}
