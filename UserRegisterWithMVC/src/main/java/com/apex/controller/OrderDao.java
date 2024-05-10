package com.apex.controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apex.model.Order;

public class OrderDao {

	
	public List<Order> getOrderList(){
		List<Order> orderlist = new ArrayList<Order>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root",
					"admin");
			String query = "select * from orders";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (connection != null) {
				while (rs.next()) {
					int orderID = rs.getInt("orderID");
					String itemName = rs.getString("itemName");
					Date orderDate = rs.getDate("orderDate");
					int quantity = rs.getInt("quantity");
					
					Order order = new Order(orderID, itemName, orderDate, quantity);
					orderlist.add(order);
				}

			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderlist;
	}
}
