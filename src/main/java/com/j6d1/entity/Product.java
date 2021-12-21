package com.j6d1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String name;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String image;
	
	@Column(columnDefinition = "float")
	private Double price;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate", columnDefinition = "date")
	private Date createDate;
	
	@Column(columnDefinition = "bit")
	private Boolean avaiable;

	@ManyToOne
	@JoinColumn(name = "Categoryid")
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
