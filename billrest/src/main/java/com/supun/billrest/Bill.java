/*
 * Author : S.M.Suriyaarachchi
 * Student ID: IT20187514
 * 
 * Usage : This is the domain class.
 * */
package com.supun.billrest;

public class Bill {

	private int billId;
	private int accountNo;
	private double dueAmount;
	private double totalAmount;
	

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", accountNo=" + accountNo + ", dueAmount=" + dueAmount + ", totalAmount="
				+ totalAmount + "]";
	}
	
	
	
}
