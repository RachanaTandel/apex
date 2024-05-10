<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>
		<%
		String error = (String) session.getAttribute("error");
		if (error != null) {
			out.print(error);
			session.setAttribute("error", null);
		}
		if (session.getAttribute("firstName") != null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("successPage.jsp");
			requestDispatcher.forward(request, response);
		}
		%>
	</h1>


	<form action="<%= request.getContextPath() %>/loginServlet" method="post">
		UserName : <input type="text" name="username"><br>
		Password : <input type="text" name="password"><br> <input
			type="submit">
	</form>
</body>
</html>