package com.j6d1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.j6d1.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{

	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE','STAF','ADMIN')")
//	@Query("SELECT DISTINCT ar.account FROM Authority ar")
	List<Account> getAdministrators();

}
