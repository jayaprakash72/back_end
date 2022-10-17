package com.pfms.Personal_Finance_Management.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Goal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gid;
	@Column
	private String goalName;
	@Column
	private double goalAmount;
	@Column
	private double varAmount;
	private long uid;
	private long cid;
	
	public double getVarAmount() {
		return varAmount;
	}

	public void setVarAmount(double varAmount) {
		this.varAmount = varAmount;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	@ManyToOne
	@JoinColumn(name = "uid",nullable = false, referencedColumnName = "user_id", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "cid",nullable = false, referencedColumnName = "categoryId", insertable = false, updatable = false)
	private Category category;
	
	public long getGid() {
		return gid;
	}

	public void setGid(long gid) {
		this.gid = gid;
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public double getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(double goalAmount) {
		this.goalAmount = goalAmount;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public Goal(long gid, String goalName, double goalAmount, long uid,long cid,double varAmount) {
		super();
		this.gid = gid;
		this.goalName = goalName;
		this.goalAmount = goalAmount;
		this.varAmount=varAmount;
		this.uid = uid;
		this.cid=cid;
	}

	public Goal() {
		
	}
	
	
	
}
