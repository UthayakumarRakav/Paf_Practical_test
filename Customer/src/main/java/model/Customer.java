package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertCustomer(String cname, String gender,String phone,String email,String city, String region)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into customer(`cID`,`cname`,`gender`,`phone`,`email`,`city`,`region`)" + " values (?,?,?,?,?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, cname);
		preparedStmt.setString(3, gender);
		preparedStmt.setString(4, phone);
		preparedStmt.setString(5, email);
		preparedStmt.setString(6, city);
		preparedStmt.setString(7, region);
		
	// execute the statement

	 preparedStmt.execute();
	 con.close();
	 String newItems = readCustomer();
	 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
	 } 
	 return output;
	 }
	public String readCustomer()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	//Prepare the html table to be displayed
	output = "<table border=\"1\"><tr><th>name</th><th>gender</th><th>phone</th><th>email</th><th>city</th><th>region</th><th>Update</th><th>Remove</th></tr>";

	String query = "select * from customer";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
		
		String cname = rs.getString("cname");
		String gender =rs.getString("gender");
		String email = rs.getString("email");
		
		String city = rs.getString("city");
		String phone= Integer.toString(rs.getInt("phone"));
		String region= rs.getString("region");
		String cID= Integer.toString(rs.getInt("cID"));

	// Add into the html table
	output += "<tr><td>"+ cname + "</td>";
	output += "<td>" + gender + "</td>";
	output += "<td>" + phone + "</td>";
	output += "<td>" + email + "</td>";
	output += "<td>" + city + "</td>";
	output += "<td>" + region + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-itemid='" + cID + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-itemid='" + cID + "'></td></tr>";
	} 
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateCustomer(String cID,String cname, String gender,String phone,String email,String city, String region)

	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = " update customer set cname=?,gender=?, phone=?, email=?, city=?,region=? where cID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	    preparedStmt.setString(1, cname);
		preparedStmt.setString(2, gender);
		preparedStmt.setInt(3, Integer.parseInt(phone));
		preparedStmt.setString(4, email);
		preparedStmt.setString(5, city);
		preparedStmt.setString(6, region);
		preparedStmt.setInt(7, Integer.parseInt(cID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newItems = readCustomer();
	 output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
	 System.err.println(e.getMessage());
	 } 
	 return output;
	 }
	public String deleteCustomer(String ID)
	 {
		String result = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the Database";
			}
			
			String query = " delete from customer where cID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(ID));
			preparedStmt.execute();
			
			con.close();
			String newItems = readCustomer();
			result = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			 }
			 catch (Exception e)
			 {
				 result = "{\"status\":\"error\", \"data\":\"Error while deleting the Customer.\"}";
			 System.err.println(e.getMessage());
			 } 
		
		return result;	
	 }
	} 