<%@page import="entidades.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tipo de Usuarios</title>
</head>
<body>
	<a href="ServletTipoUsuario?Param=1">Mostrar Tipo Usuarios</a>

	<%
		ArrayList<TipoUsuario> listaTipoUsuario = null;
	if (request.getAttribute("listaU") != null) {
		listaTipoUsuario = (ArrayList<TipoUsuario>) request.getAttribute("listaU");
	}
	%>

	<table id="table_id" class="display">
		<thead>
			<tr>
				<th>ID</th>
				<th>Tipo</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<%
				if (listaTipoUsuario != null)
				for (TipoUsuario tipo : listaTipoUsuario) {
			%>
			<tr>
				<td><%=tipo.getId()%> <input type="hidden" name="idUsuario"
					value="<%=tipo.getId()%>"></td>
				<td><%=tipo.getTipo()%></td>
				<td><input type="submit" name="btnEliminar2" value="Eliminar">
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
<script src="js/espanol.js"></script>	
</body>
</html>