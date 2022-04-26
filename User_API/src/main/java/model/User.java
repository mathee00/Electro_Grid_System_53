package model;
import java.sql.*;
public class User{
	
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "root");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertUsers(String name, String nic, String address, String status)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 

 String query = " insert into users(`UseraccountNo`,`Username`,`Usernic`,`Useraddress`,`Userstatus`)"
 + " values (?, ?, ?, ?, ?)";
 
 
 PreparedStatement preparedStmt = con.prepareStatement(query);
 
 
 
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, name);
 preparedStmt.setString(3, nic);
 preparedStmt.setString(4, address);
 preparedStmt.setString(5, status);
 
 
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the item.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readUsers()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }


 
 output = "<table border='1'><tr><th>User Account No</th><th>User Name</th>" +
 "<th>User NIC</th>" +
 "<th>User Address</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from users";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);


 while (rs.next())
 {
 String UseraccountNo  = Integer.toString(rs.getInt("UseraccountNo "));
 String Username = rs.getString("Username");
 String Usernic  = rs.getString("Usernic");
 String Useraddress = Double.toString(rs.getDouble("Useraddress"));
 String Userstatus = rs.getString("Userstatus");
 
 

 output += "<tr><td>" + UseraccountNo  + "</td>";
 output += "<td>" + Username + "</td>";
 output += "<td>" + Useraddress + "</td>";
 output += "<td>" + Userstatus + "</td>";


 
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value='" + UseraccountNo 
 + "'>" + "</form></td></tr>";
 }
 con.close();


 
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the users.";
 System.err.println(e.getMessage());
 }
 return output;
 }


public String updateUsers(String No, String name, String nic, String address, String status)


 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for updating."; }
 
 
 String query = "UPDATE users SET UseraccountNo=?,Username=?,Usernic=?,Useraddress=?WHERE UseraccountNo =?";
 
 PreparedStatement preparedStmt = con.prepareStatement(query);


 preparedStmt.setString(1, No);
 preparedStmt.setString(2, name);
 preparedStmt.setString(3, nic);
 preparedStmt.setString(4, address);
 preparedStmt.setInt(5, Integer.parseInt(No));


 
 preparedStmt.execute();
 con.close();
 output = "Updated successfully";
 }
 catch (Exception e)
 {
 output = "Error while updating the user.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String deleteUsers(String UseraccountNo )
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }


 String query = "delete from users where UseraccountNo =?";
 PreparedStatement preparedStmt = con.prepareStatement(query);


 preparedStmt.setInt(1, Integer.parseInt(UseraccountNo));
 
 preparedStmt.execute();
 con.close();
 output = "Deleted successfully";
 }
 catch (Exception e)
 {
 output = "Error while deleting the item.";
 System.err.println(e.getMessage());
 }
 return output;
 }

} 