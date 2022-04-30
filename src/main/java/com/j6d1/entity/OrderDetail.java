package com.j6d1.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "int")
	private Integer id;
	
	@Column(columnDefinition = "float")
	private Double price;
	
	@Column(columnDefinition = "int")
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "Orderid")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "Productid")
	private Product product;
	
	
	
	
	
	
	
	
	
}
