package com.apex.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.apex.model.User;

public class UserDao {
	public int registerUser(User user) throws ClassNotFoundException{
		
		int result = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
			PreparedStatement preparedStatement = connection.prepareStatement("insert into user values(?,?,?,?)");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getUserName());
			preparedStatement.setString(4, user.getPassword());
			result = preparedStatement.executeUpdate();
			System.out.print(result);
			preparedStatement.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean checkUser(String userName) throws SQLException {
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
			PreparedStatement statement = connection.prepareStatement("select * from user where username=?");
			statement.setString(1, userName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				userName = resultSet.getString(1);
				return true; 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
			
}
