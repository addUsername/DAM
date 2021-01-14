<%@page import="dam2.dii.p2.Contacto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Contacto> contactos = (ArrayList<Contacto>) application.getAttribute("contactos");
		if(contactos != null && contactos.size() > 0) {
	%>
	<table>
		<tr> 
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Email</th>
			<th>Telefono</th>
			<th>Comentarios</th>	
		</tr>
		<% for(Contacto con: contactos){ %>
			<tr>
				<td><%=con.getName() %></td>
				<td><%=con.getSurname() %></td>
				<td><%=con.getEmail() %></td>
				<td><%=con.getPhone() %></td>
				<td><%=con.getComments() %></td>
			</tr>
		<%}} %>
	</table>
	<form action = "post" method = "post" class="form" id="form">
			
			<p>Nombre:</p> <input type = "text" name = "name"/>
			<p>Apellido:</p> <input type = "text" name = "surname" />
			<p>Email:</p> <input type = "text" name = "email" />
			<p>Telefono:</p> <input type = "text" name = "phone" />
			<p>Comentarios:</p> <input type = "text" name = "comments" />		
			<input id="button" type = "submit" value = "Submit" />
</form>
</body>
</html>