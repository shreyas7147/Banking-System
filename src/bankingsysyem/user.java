package bankingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class user {
	private Scanner sc;
	private Connection con;
	public user(Scanner sc,Connection con) {
		this.sc=sc;
		this.con=con;
	}

	public void registration() {
		sc.nextLine();
		System.out.println("Full Name : ");
		String Full_name = sc.nextLine();
		System.out.println("Email : ");
		String Email = sc.nextLine();
		System.out.println("Password : ");
		String Password = sc.nextLine();
		
		if (user_exist(Email)) {
			System.out.println ("user already exist for this email address");
			return ;
		}
		String query = "INSERT INTO user(fullname,email,password) VALUES(?,?,?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,  Full_name);
			preparedStatement.setString(2,  Email);
			preparedStatement.setString(3,  Password);
			int affectedRows = preparedStatement.executeUpdate();
			if(affectedRows>0) {
				System.out.println("Registration Successfull");
			} else {
				System.out.println("Registratin Failed");
			}

		} catch(Exception e) {
			System.out.println(e);
		}

	}
	
	private boolean user_exist(String email) {
		String query = "SELECT * FROM user WHERE email = ?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,  email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			} else {
				return false;
			}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	

	public String login() {
		sc.nextLine();
		System.out.println("Email : ");
		String Email = sc.nextLine();
		System.out.println("Password : ");
		String Password = sc.nextLine();
		
		String login_query= "SELECT*FROM user WHERE email = ? AND password=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(login_query);
			preparedStatement.setString(1,  Email);
			preparedStatement.setString(2,  Password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return Email;
			}
			else {
				return null;
			}
		}
		catch (SQLException e) {
e.printStackTrace();
	}
return null;
}
}
	