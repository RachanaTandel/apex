package com.apex.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import com.apex.model.Order;
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private OrderDao orderDao;
	public void init() {
		userDao = new UserDao();
		orderDao = new OrderDao();
	}   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
			String firstName = "";
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			if (null != userName && null != password) {
				if (connection != null) {
					PreparedStatement statement = connection
							.prepareStatement("select * from user where username=? and password=?");
					statement.setString(1, userName);
					statement.setString(2, password);
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
						firstName = resultSet.getString(1);
					}
				}
			}
			HttpSession session = request.getSession();
			if (firstName.length() != 0) {
				session.setAttribute("firstName", firstName);
				
				List<Order> orderList = orderDao.getOrderList();
				//request.setAttribute("orderList", orderList);
				request.getSession().setAttribute("orderList", orderList);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("./views/successPage.jsp");
				requestDispatcher.forward(request, response);
			} else {
				session.setAttribute("error", "Invalid Login");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("./views/loginPage.jsp");
				requestDispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
