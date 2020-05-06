package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/paymenth?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertpayment(String paydetails, String date, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into mytable(`pid`,`paydetails`,`date`,`amount`)" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, paydetails);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, amount);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readpayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readpayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading purpose.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" id='divPaymentsGrid'>"
					+ "<tr>"
					+ "<th>PaymentName</th>"
					+ "<th>PaymentDate</th>"
					+ "<th>PaymentAmount</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";
			String query = "select * from mytable";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String pid = Integer.toString(rs.getInt("pid"));
				String paydetails = rs.getString("paydetails");
				String date = rs.getString("date");
				String amount= rs.getString("amount");
				// Add into the html table
				 output += "<tr>";
				 output += "<td><input id='hidpidUpdate' name='hidpidUpdate' type='hidden' value='" + pid + "'>" + paydetails+ "</td>";
				 output += "<td>" + date + "</td>";
				 output += "<td>" + amount + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn-secondary'></td>"
						+ "<td><input name='pid' type='button' value='Remove' class='btnRemove btn-danger' data-pid='"
						+ pid + "'>" + "</td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the paymet details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String pid, String paydetails, String date, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE mytable SET paydetails=?,date=?,amount=? WHERE pid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, paydetails);
			preparedStmt.setString(2, date);
			preparedStmt.setString(3, amount);
			preparedStmt.setInt(4, Integer.parseInt(pid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readpayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Payment details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String delatepayment(String pid) {
		String output = "";
		try {
		Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from mytable where pid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readpayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Payment data.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public static void main(String[] args) {
		new Payment().insertpayment("sdf", "safsaf", "123.23");
		//new Item().delatepayment("1");
	}

}
