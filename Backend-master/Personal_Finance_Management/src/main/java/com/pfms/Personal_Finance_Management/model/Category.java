package com.pfms.Personal_Finance_Management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long categoryId;
	@Column
	private String category;
	private long uid;

	@ManyToOne
	@JoinColumn(name = "uid",nullable = false, referencedColumnName = "user_id", insertable = false, updatable = false)
	private User user;
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public Category(long categoryId, String category, long uid) {
		super();
		this.categoryId = categoryId;
		this.category = category;
		this.uid = uid;
	}

	public Category() {
		
	}
	

	

}
