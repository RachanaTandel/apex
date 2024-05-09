<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>

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
	 try
   {
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "admin");
       String query="select * from orders";
       Statement stmt=connection.createStatement();
       ResultSet rs=stmt.executeQuery(query);
       if (connection != null) {
       		while(rs.next())
       		{  %>

		<tr>
			<td>
				<%out.println(rs.getInt("orderID")); %>
			</td>
			<td>
				<%out.println(rs.getString("itemName")); %>
			</td>
			<td>
				<%out.println(rs.getDate("orderDate")); %>
			</td>
			<td>
				<%out.println(rs.getInt("quantity")); %>
			</td>
		</tr>

		<%
       }
    }
   %>

	</table>
	<%
        rs.close();
        stmt.close();
        connection.close();
   }
   catch(Exception e)
   {
        e.printStackTrace();
   }
   %>

</body>
</html>