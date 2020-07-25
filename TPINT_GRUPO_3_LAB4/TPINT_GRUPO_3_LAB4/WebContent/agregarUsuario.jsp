<%@page import="entidades.TipoUsuario"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Profesor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Usuario</title>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item active" aria-current="page">Nuevo
				Usuario</li>
		</nav>
	<div class="container">
		<form action="ServletUsuarios" method="post" style="margin: 40px">
			<div class="form-group row">
				<label for="validationServer04" class="col-sm-2 col-form-label">Profesor</label>
				<div class="col-sm-2">
					<select name="cmbProfesor" class="custom-select "
						id="validationServer04" required>
						<option selected disabled value="">Seleccione...</option>
						<%
							ArrayList<Profesor> listaProfesor = null;
							if (request.getAttribute("listaProfes") != null) {
								listaProfesor = (ArrayList<Profesor>) request.getAttribute("listaProfes");
							}
						%>
						<%
							if (listaProfesor != null)
								for (Profesor prof : listaProfesor) {
						%>
						<option value=<%=prof.getLegajo()%>><%=prof.getLegajo()%>
							-
							<%=prof.getNombre()%>
							<%=prof.getApellido()%></option>
						<%
							}
						%>

					</select>
				</div>
			</div>
			<div class="form-group row ">
				<label for="staticEmail" class="col-sm-2 col-form-label">Usuario</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="inputUsuario"
						name="txtUsuario" autocomplete="off"  onKeyPress="return onlyLetter(event)" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputPassword" class="col-sm-2 col-form-label">Contraseña</label>
				<div class="col-sm-2">
					<input type="password" class="form-control" id="inputPassword"
						name="txtClave" autocomplete="off" required>
				</div>
			</div>
			<br>
			<input Id="btnGuardar" name="btnGuardar" class="btn btn-primary"
				type="submit" Value="Guardar"> <a Id="Retroceder"
				name="Retroceder" class="btn btn-secondary" type="submit"
				href="ServletUsuarios?Param=1">Volver</a>
		</form>
		<%
			if (request.getAttribute("msj") != null) {
		%>
		<div class="alert alert-danger" role="alert"><%=request.getAttribute("msj")%></div>
			
		<%
			}
		%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
	<jsp:include page="scriptValidaciones.jsp"></jsp:include>
</body>
</html>