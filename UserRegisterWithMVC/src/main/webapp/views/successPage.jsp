<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.util.List"%>
<%@ page import="com.apex.model.Order"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	if (session.getAttribute("firstName") == null) {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginPage.jsp");
		requestDispatcher.forward(request, response);
	}
	%>
	<h1>
		Welcome User
		<%=session.getAttribute("firstName")%>!
	</h1>


	<table border="2">
		<tr>
			<td>Order ID</td>
			<td>Item Name</td>
			<td>Order Date</td>
			<td>Quantity</td>
		</tr>
		 <% 
            List<Order> orderList = (List<Order>) session.getAttribute("orderList");
            if (orderList != null) {
                for (Order order : orderList) {
        %>
		<tr>
			<td>
				<%out.println(order.getOrderID()); %>
			</td>
			<td>
				<%out.println(order.getItemName()); %>
			</td>
			<td>
				<%out.println(order.getOrderDate()); %>
			</td>
			<td>
				<%out.println(order.getQuantity()); %>
			</td>
		</tr>
		<%
       }
           }
   %>

	</table>

</body>
</html>