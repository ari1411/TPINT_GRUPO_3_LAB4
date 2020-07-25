<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Curso"%>
<%@page import="entidades.Alumno"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Ver curso</title>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<style>
.bs-example {
	margin: 20px;
}
</style>

</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>

	<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">Ver
			Curso</li>
	</ol>
	</nav>

	<%
		Curso curso = null;
		if (request.getAttribute("CursoElim") != null) {
			curso = (Curso) request.getAttribute("CursoElim");
		}
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-3 mb-3">
				<label for="sel1">Materia:</label> <select name="cmbMateria"
					class="form-control " id="validationServer04" disabled="true">
					<option value=<%=curso.getIdMateria()%>><%=curso.getMateria()%></option>
				</select>
			</div>

			<div class="col-md-3 mb-3">
				<label for="sel1">Turno:</label> <select class="form-control"
					id="sel1" name="cmbTurno" disabled="true">
					<option //
							value=<%=curso.getIdTurno()%>><%=curso.getTurno()%></option>
				</select>
			</div>

			<div class="col-md-3 mb-3">
				<label for="sel1">Cuatrimestre N°:</label> <select
					class="form-control" id="sel1" name="cmbCuatrimestre"
					disabled="true">
					<option><%=curso.getCuatrimestre()%></option>
				</select>
			</div>

			<div class="col-md-3 mb-3">
				<label for="sel1">Año:</label> <select class="form-control"
					id="sel1" name="cmbAnio" disabled="true">
					<option><%=curso.getAnio()%></option>

				</select>
			</div>

		</div>

		<br>

		<H4>Alumnos:</H4>
		<table id="AlumnosCursoD" name="tableAlumnos" class="display"
			style="width: 100%">
			<thead>
				<tr>
					<th>Legajo</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>DNI</th>
					<th>Fecha nacimiento</th>
<!-- 					<th>Direccion</th> -->
<!-- 					<th>Localidad</th> -->
<!-- 					<th>Provincia</th> -->
<!-- 					<th>Telefono</th> -->
					<th>Mail</th>
				</tr>
			</thead>
			<tbody>
				<%
					ArrayList<Alumno> listaAlumno = null;
					if (request.getAttribute("ListaAlumnos") != null) {
						listaAlumno = (ArrayList<Alumno>) request.getAttribute("ListaAlumnos");
					}
				%>
				<%
					if (listaAlumno != null)
						for (Alumno alumno : listaAlumno) {
				%>
				<tr>
					<td><%=alumno.getLegajo()%></td>
					<td><%=alumno.getNombre()%></td>
					<td><%=alumno.getApellido()%></td>
					<td><%=alumno.getDni()%></td>
					<td><%=alumno.getFechaNac()%></td>
<%-- 					<td><%=alumno.getDireccion()%></td> --%>
<%-- 					<td><%=alumno.getLocalidad().getNombreLoc()%></td> --%>
<%-- 					<td><%=alumno.getLocalidad().getProvincia().getNombreProv()%></td> --%>
<%-- 					<td><%=alumno.getTelefono()%></td> --%>
					<td><%=alumno.getMail()%></td>
				</tr>
				<%
					}
				%>


			</tbody>

		</table>

		<a Id="Retroceder" name="Retroceder" class="btn btn-primary"
			type="submit" href="ServletCurso?listCoursesProfessor=1">Volver</a>
		<!-- 				<a Id="Retroceder" name="Retroceder" class="btn btn-secondary" -->
		<!-- 				type="submit" href="ServletCurso?listCourses=1">Volver</a> -->

	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script src="js/espanol.js"></script>	
</body>
</html>
