package com.pfms.Personal_Finance_Management.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Piechart {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long dataId;
	@Column
	private String name;
	@Column
	private BigDecimal value;
	
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public BigDecimal getvalue() {
		return value;
	}
	public void setvalue(BigDecimal value) {
		this.value = value;
	}
	public Piechart(String name, BigDecimal value) {
		super();
		this.name = name;
		this.value = value;
	}
	public Piechart() {
		
	}
	
	

}
