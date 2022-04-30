package com.j6d1.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(columnDefinition = "nvarchar(50)")
	private String username;
	
	@Column(columnDefinition = "varchar(250)")
	private String password;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String fullname;
	
	@Column(columnDefinition = "varchar(50)")
	private String email;
	
	@Column(columnDefinition = "varchar(255)")
	private String photo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Authority> authorities;

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", fullname=" + fullname + "]";
	}
	
	
	

}
