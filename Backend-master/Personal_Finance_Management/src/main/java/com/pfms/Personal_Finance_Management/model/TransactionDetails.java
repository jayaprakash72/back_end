package com.pfms.Personal_Finance_Management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String date;
	@Column
	private String category;
	@Column
	private String bank_name;
	@Column
	private double amount;
	@Column
	private long uid;

	
	@ManyToOne
	@JoinColumn(name = "uid",nullable = false, referencedColumnName = "user_id", insertable = false, updatable = false)
	private User user;
//	
//	@ManyToOne
//	@JoinColumn(name = "wid",nullable = false, referencedColumnName = "walletId", insertable = false, updatable = false)
//	private Wallet wallet;

//	@ManyToOne
//	private Category category_id;
//
//	public long getWid() {
//		return wid;
//	}
//
//	public void setWid(long wid) {
//		this.wid = wid;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public TransactionDetails(long id, String date, String category, String bank_name, double amount, long uid) {
		super();
		this.id = id;
		this.date = date;
		this.category = category;
		this.bank_name = bank_name;
		this.amount = amount;
		this.uid = uid;
		//this.wid=wid;
	}

	public TransactionDetails() {

	}
	


}
