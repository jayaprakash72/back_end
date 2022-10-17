package com.pfms.Personal_Finance_Management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long walletId;
	private String walletName;
	private double expenseLimit;
	private double wallet_amount;
	private long uid;
	//private long wid;
	
	
	
	@ManyToOne
	@JoinColumn(name = "uid",nullable = false, referencedColumnName = "user_id", insertable = false, updatable = false)
	private User user;
	

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public double getExpenseLimit() {
		return expenseLimit;
	}

	public void setExpenseLimit(double expenseLimit) {
		this.expenseLimit = expenseLimit;
	}

	public double getWallet_amount() {
		return wallet_amount;
	}

	public void setWallet_amount(double wallet_amount) {
		this.wallet_amount = wallet_amount;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public Wallet(long walletId, String walletName, double expenseLimit, double wallet_amount, long uid) {
		super();
		this.walletId = walletId;
		this.walletName = walletName;
		this.expenseLimit = expenseLimit;
		this.wallet_amount = wallet_amount;
		this.uid = uid;
	}

	public Wallet() {
	
	}





	


}
