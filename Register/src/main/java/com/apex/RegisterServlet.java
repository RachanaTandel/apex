package com.apex;

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

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
			String firstName = request.getParameter("username");
			String lastName = request.getParameter("lastname");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			
			HttpSession session = request.getSession();			
			if(userName != null) {
				if (connection != null) {
					PreparedStatement statement = connection.prepareStatement("select * from user where username=?");
					statement.setString(1, userName);
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
						userName = resultSet.getString(1);
						System.out.println("Username already exists");
						session.setAttribute("error", "Username already exists");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("registerPage.jsp");
						requestDispatcher.forward(request, response);
					}
				}
			}
			if (firstName != "" && lastName != "" &&  userName != "" && password != "") {
				if (connection != null) {
					PreparedStatement statement = connection.prepareStatement("insert into user values(?,?,?,?)");
					statement.setString(1, firstName);
					statement.setString(2, lastName);
					statement.setString(3, userName);
					statement.setString(4, password);
					int executeUpdate = statement.executeUpdate();
					System.out.print(executeUpdate);
					statement.close();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginPage.jsp");
					requestDispatcher.forward(request, response);
				}
				
			} else {
				System.out.println("Enter all fields");
				session.setAttribute("error", "Enter all fields");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("registerPage.jsp");
				requestDispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
