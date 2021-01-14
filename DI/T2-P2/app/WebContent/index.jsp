<%@page import="dam2.dii.p2.Contacto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>P2</title>
<style><%@include file="/WEB-INF/resources/css/style.css"%></style>
</head>
<body>
	<h1> DII P2</h1>
	<div class="container">
	<% if (request.getAttribute("error") != null){ %>
		<h2><%=request.getAttribute("error")%></h2>
	<%} %>
	<form action = "post" method = "post" class="form">
			
			<p>Nombre:</p> <input type = "text" name = "name"/>
			<p>Apellido:</p> <input type = "text" name = "surname" />
			<p>Email:</p> <input type = "email" name = "email" />
			<p>Telefono:</p> <input type = "number" min="600000000" max="799999999 "name = "phone" />
			<p>Comentarios:</p> <textarea type = "text" maxlength="200" rows="10" cols="30" name = "comments"></textarea>		
			<input id="button" type = "submit" value = "Submit" />
	</form>
	<%
		List<Contacto> contactos = (ArrayList<Contacto>) application.getAttribute("contactos");
		if(contactos != null && contactos.size() > 0) {
	%>
	<table class="table">
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
	</div>
</body>
</html>