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
		}%>
	</h1>
	<form action="<%= request.getContextPath() %>/registerServlet" method="post">
		First Name : <input type="text" name="firstname" ><br>
		Last Name : <input type="text" name="lastname" ><br>
		UserName : <input type="text" name="username" ><br>
		Password : <input type="password" name="password" ><br> <input
			type="submit">
	</form>
</body>
</html>