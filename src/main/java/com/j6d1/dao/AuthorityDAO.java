package com.j6d1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.j6d1.entity.Account;
import com.j6d1.entity.Authority;

public interface AuthorityDAO extends JpaRepository<Authority, Integer>{

	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);

}
