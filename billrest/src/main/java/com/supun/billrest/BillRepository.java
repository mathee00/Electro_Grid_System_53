/*
 * Author : S.M.Suriyaarachchi
 * Student ID: IT20187514
 * 
 * Usage : Implementing the back-end implementation of the CRUD operations.
 * */

package com.supun.billrest; 

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class BillRepository {
	
	//Creating db connection
	Connection con = null;
	
	//Creating a constructor
	public BillRepository() {
		
		String url = "jdbc:mysql://localhost:3308/egbilldb";
		String username = "root";
		String password = "";
		try {
			//To load the driver
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Successfully connected to db!!");
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Bill> getBills(){
		//Creating a bills ArrayList
		List<Bill> bills = new ArrayList<>();
		
		//SQL query for getting data from bill table
		String sql = "SELECT * FROM bill";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Bill b = new Bill();
				b.setBillId(rs.getInt(1));
				b.setAccountNo(rs.getInt(2));
				b.setDueAmount(rs.getDouble(3));
				b.setTotalAmount(rs.getDouble(4));
				
				//Assigning the values to the bills list
				bills.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bills;
	}
	
	//Method to only get a single data set
	public Bill getBill(int id) {
		String sql = "SELECT * FROM bill WHERE billId="+id;
		Bill b1 = new Bill();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				b1.setBillId(rs.getInt(1));
				b1.setAccountNo(rs.getInt(2));
				b1.setDueAmount(rs.getDouble(3));
				b1.setTotalAmount(rs.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b1;
	}
	
	
	//Method to insert data 
	public void create(Bill b2) {
		
		//These ? question marks are place-holders for prepared statement
		String sql = "INSERT INTO bill VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, b2.getBillId());
			st.setInt(2, b2.getAccountNo());
			st.setDouble(3, b2.getDueAmount());
			st.setDouble(4, b2.getTotalAmount());
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//Method to update data 
	public void update(Bill b3) {
		
		//These ? question marks are place-holders
		String sql = "UPDATE bill SET accountNo=?, dueAmount=?, totalAmount=? WHERE billId=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(4, b3.getBillId());
			st.setInt(1, b3.getAccountNo());
			st.setDouble(2, b3.getDueAmount());
			st.setDouble(3, b3.getTotalAmount());
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	//Method to delete data
	public void delete(int id) {
		String sql = "DELETE FROM bill WHERE billId="+id;
		
		try {
			PreparedStatement st = con.prepareStatement(sql);	
			st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);		
		}
	}
	
}
