<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0 maximum-scale=1.0, user-scalable=no">  
<title>Practica 1</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<div class="container">
<% if(request.getAttribute("user") != null) {%>
	<h1 class="header">Hola <%=request.getAttribute("user")%> </h1>
<% }else if (request.getAttribute("error") != null){ %>
	<h1 class="header">Se ha producido un error <%=request.getAttribute("error")%> </h1>
<%} else{ %>
	<h1 class="header"> Login </h1>
<%} %>
	<form action="hello" method="post" class="form">		
		<input type = "text" name = "user" placeholder="Introduce nombre"/>
		<input type = "password" name = "pass" placeholder="Introduce contraseña"/>
		<input type = "password" name = "pass2" placeholder="Repite contraseña"/>
		<input id="button" type = "submit" value = "Submit" title="submit" />
		<a title="Punto 8 - Funcionalidad adicional " href="https://mylog1n.herokuapp.com/"> <h2>version extendida</h2> </a>
	</form>	
</div>
</body>
</html>