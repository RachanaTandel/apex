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

import com.apex.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("username");
		String lastName = request.getParameter("lastname");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(password);

		if (userName != "" && lastName != "" && userName != "" && password != "") {
			try {
				if (userDao.checkUser(userName) == true) {
					System.out.println("Username already exists");
					session.setAttribute("error", "Username already exists");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("./views/registerPage.jsp");
					requestDispatcher.forward(request, response);
				} else {
					userDao.registerUser(user);
					response.sendRedirect("./views/loginPage.jsp");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Enter all fields");
			session.setAttribute("error", "Enter all fields");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("./views/registerPage.jsp");
			requestDispatcher.forward(request, response);
		}

	}

}
