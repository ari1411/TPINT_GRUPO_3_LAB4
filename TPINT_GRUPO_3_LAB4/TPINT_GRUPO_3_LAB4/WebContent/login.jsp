<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
<link href="Css/Style.css" rel="StyleSheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="navbar-brand" href="#"> <img
				src="img/logo.png" alt="Logo" style="width: 40px;">
		</a></li>
	</nav>
	

	<div class="login-form">
		<form action="ServletUsuarios?Param=2" method="post" onsubmit="return error();">
			<h2 class="text-center">Login</h2>
			<div class="form-group">
				<input type="text" class="form-control" name="txtUsuario"
					placeholder="Usuario" required="required" autocomplete="off">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" name="txtClave"
					placeholder="Clave" required="required">
			</div>
			<div class="form-group">
				<input  type="submit" value="Ingresar"
					class="btn btn-primary btn-block" name="btnIngresar">
					
				
					
			</div>
				<%
					if (request.getAttribute("UsuarioYaExiste") != null) {
				%>
				<div class="alert alert-danger" role="alert">Usuario y/o contraseña incorrecta</div>
				<%}%>

			
		</form>
	</div>
	

	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
	
	

</body>
</html>