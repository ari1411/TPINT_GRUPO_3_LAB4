<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Curso"%>
<%@page import="entidades.Alumno"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Eliminar Curso</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
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
		<li class="breadcrumb-item active" aria-current="page">Eliminar
			Curso</li>
	</ol>
	</nav>

	<%
		Curso curso = null;
		ArrayList<Alumno> alum = null;

		if (request.getAttribute("CursoElim") != null) {
			curso = (Curso) request.getAttribute("CursoElim");
		}

		if (request.getAttribute("ListaAlumnos") != null) {
			alum = (ArrayList<Alumno>) request.getAttribute("ListaAlumnos");
		}
	%>

	<div class="container">
		<form style="margin: 40px" method="post" action="ServletCurso">
			<div class="row">
				<div class="col-md-3 mb-3">
					<label for="sel1">Materia:</label> <select name="cmbMateria"
						class="custom-select" id="validationServer04" disabled="true">
						<option value=<%=curso.getIdMateria()%>><%=curso.getMateria()%></option>
					</select>
				</div>
				
				<div class="col-md-3 mb-3">
					<label for="sel1">Turno:</label> <select
						class="custom-select" id="sel1" name="cmbTurno" disabled="true">
						<option selected style="visibility: hidden"
							value=<%=curso.getIdTurno()%>><%=curso.getTurno()%></option>
					</select>
				</div>

				<div class="col-md-3 mb-3">
					<label for="sel1">Cuatrimestre N°:</label> <select
						class="custom-select" id="sel1" name="cmbCuatrimestre"
						disabled="true">
						<option><%=curso.getCuatrimestre()%></option>
					</select>
				</div>

				<div class="col-md-3 mb-3">
					<label for="sel1">Año:</label> <select class="custom-select"
						id="sel1" name="cmbAnio" disabled="true">
						<option><%=curso.getAnio()%></option>

					</select>
				</div>

			</div>


			<br> <br>
			<div class="row">
				<div class="col-md-3 mb-3">
					<H4>Profesor:</H4>
				</div>
				<div class="col-md-3 mb-3">
					<select name="cmbProfesor" class="custom-select "
						id="validationServer04" disabled="true">
						<option value=<%=curso.getLegajoProf()%>><%=curso.getProfesor()%></option>
					</select>
				</div>
			</div>
			<br> <br>


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
						<th>Direccion</th>
						<th>Localidad</th>
						<th>Provincia</th>
						<th>Telefono</th>
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
						<td><%=alumno.getDireccion()%></td>
						<td><%=alumno.getLocalidad().getNombreLoc()%></td>
						<td><%=alumno.getLocalidad().getProvincia().getNombreProv()%></td>
						<td><%=alumno.getTelefono()%></td>
						<td><%=alumno.getMail()%></td>
					</tr>
					<%
						}
					%>


				</tbody>

			</table>

			<h4>¿Seguro deseas eliminarlo?</h4>
			<br> <a Id="EliminarCurso" name="EliminarCurso"
				class="btn btn-danger" type="submit"
				href="ServletCurso?deleteConfirmedCourse=<%=curso.getId()%>">Eliminar</a>
			<a Id="Retroceder" name="Retroceder" class="btn btn-primary"
				type="submit" href="ServletCurso?listCourses=1">Volver</a>
			<!-- 				<a Id="Retroceder" name="Retroceder" class="btn btn-secondary" -->
			<!-- 				type="submit" href="ServletCurso?listCourses=1">Volver</a> -->
		</form>

		<%
			int fila = 0;
			if (request.getAttribute("cantFilas") != null) {
				fila = 1;
			}
		%>
		<%
			if (fila == 1) {
		%>
		<h2>Agregado Correctamente</h2>
		<%
			}
		%>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>
