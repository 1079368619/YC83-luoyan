package com.yc.spring.bank.bean;

public class Account implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer accountid;
	private String passsword;
	private Double balance;
	
	public Integer getAccountid() {
		return accountid;
	}
	
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	
	public String getPasssword() {
		return passsword;
	}
	
	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [accountid=" + accountid + ", passsword=" + passsword + ", balance=" + balance + "]";
	}
	
}
