<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cambiar Clave</title>
<link href="Css/Style.css" rel="StyleSheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>
	<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">Cambiar
			Clave</li>
	</ol>
	</nav>

	<div class="container">
		<form action="ServletUsuarios?changePass=1" method="post">
			<div class="form-row">
				<div class="col-md-3 mb-3">
					<label for="txtClave">Ingrese nueva clave</label> <input
						type="password" class="form-control" name="txtClave" required>
					<input
						value="<%=Integer.parseInt(request.getParameter("idUsuario"))%>"
						name="txtId" type="hidden" class="form-control"> 
						
						<input
						type="submit" class="btn btn-primary mt-2" value="Aceptar"
						name="btnAceptar"> 
						
						<a Id="Retroceder" name="Retroceder"
						class="btn btn-secondary mt-2" type="submit"
						href="ServletUsuarios?Param=1">Volver</a>
				</div>
			</div>
		</form>
		<%
			if (request.getAttribute("msjChange") != null) {
		%>
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong><%=request.getAttribute("msjChange")%></strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>