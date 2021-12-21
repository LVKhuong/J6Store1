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
@Table(name = "Roles")
public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "varchar(50)")
	private String id;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Authority> authorities;
}
