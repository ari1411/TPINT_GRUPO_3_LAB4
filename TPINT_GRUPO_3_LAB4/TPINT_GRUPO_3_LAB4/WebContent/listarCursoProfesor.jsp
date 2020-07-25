<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@page import="daoImpl.MateriaDaoImpl"%> --%>
<%@page import="entidades.Curso"%>
<%@page import="entidades.Turno"%>
<%@page import="entidades.Materia"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Cursos</title>
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

.MensajeServlet {
	color: navy;
	/* background-color:gray; */
	text-align: Center;
}
</style>

</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>


	<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">Mis Cursos</li>
	</ol>
	</nav>

	<%
		Curso curso = null;
		if (request.getAttribute("CursoAux") != null) {
			curso = (Curso) request.getAttribute("CursoAux");
		}
	%>
<div class="container">
	<form method="get" action="ServletCurso">
		<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<div class="row">
				<div class="col-md-4 mb-4">
					<label for="sel1">Materia:</label> <select name="cmbMateria"
						class="custom-select " id="validationServer04">
						<%
							if (curso != null && curso.getIdMateria() > 0) {
						%>
						<option selected style="visibility: hidden"
							value=<%=curso.getIdMateria()%>><%=curso.getMateria()%></option>
						<%
							} else {
						%>
						<option selected disabled value="">Seleccione...</option>
						<%
							}
							ArrayList<Materia> listaMateria = null;
							if (request.getAttribute("listaMaterias") != null) {
								listaMateria = (ArrayList<Materia>) request.getAttribute("listaMaterias");
							}
						%>
						<%
							if (listaMateria != null)
								for (Materia mate : listaMateria) {
						%>
						<option value=<%=mate.getId()%>><%=mate.getNombre()%></option>
						<%
							}
						%>
					</select>
				</div>

				<div class="col-md-2 mb-2">
					<label for="sel1">Turno:</label> <select class="form-control"
						id="sel1" name="cmbTurno">
						<%
							if (curso != null && curso.getIdTurno() > 0) {
						%>
						<option selected style="visibility: hidden"
							value=<%=curso.getIdTurno()%>><%=curso.getTurno()%></option>
						<%
							} else {
						%>
						<option selected disabled value="">Seleccione...</option>
						<%
							}
							ArrayList<Turno> listaTurnos = null;
							if (request.getAttribute("ListaTurnos") != null) {
								listaTurnos = (ArrayList<Turno>) request.getAttribute("ListaTurnos");
							}
						%>
						<%
							if (listaTurnos != null)
								for (Turno turno : listaTurnos) {
						%>
						<option value=<%=turno.getIdTurno()%>><%=turno.getTurno()%></option>
						<%
							}
						%>
					</select>
				</div>

				<div class="col-md-2 mb-2">
					<label for="sel1">Cuatrimestre N°:</label> <select
						class="form-control" id="sel1" name="cmbCuatrimestre">
						<%
							if (curso != null && curso.getCuatrimestre() > 0) {
						%>
						<option selected style="visibility: hidden"
							value=<%=curso.getCuatrimestre()%>><%=curso.getCuatrimestre()%></option>
						<%
							} else {
						%>
						<option selected disabled value="">Seleccione...</option>
						<%
							}
						%>
						<option>1</option>
						<option>2</option>
					</select>
				</div>

				<div class="col-md-2 mb-2">
					<label for="sel1">Año:</label> <select class="form-control"
						id="sel1" name="cmbAnio">
						<%
							if (curso != null && curso.getAnio() > 0) {
						%>
						<option selected style="visibility: hidden"
							value=<%=curso.getAnio()%>><%=curso.getAnio()%></option>
						<%
							} else {
						%>
						<option selected disabled value="">Seleccione...</option>
						<%
							}
						%>
						<%
							for (int x = 2020; x >= 1990; x--) {
						%>
						<option><%=x%></option>
						<%
							}
						%>
					</select>
				</div>

				<div class="col-md-2 mb-2">
					<button id="btn-filtrarProf" name="btn-filtrarProf"
						class="btn btn-outline-info " type="submit" data-toggle="tooltip" title="Filtrar">
						<i class="fa fa-search"></i> Filtrar  
					</button>
					<button id="listCoursesProfessor" name="listCoursesProfessor"
						class="btn btn-outline-secondary " type="submit" data-toggle="tooltip" title="Limpiar Filtros">
						<i class="fa fa-undo"></i> Limpiar
					</button>
				</div>

			</div>
		</ol>
		</nav>
	</form>
	</div>
	<%
		if (request.getAttribute("Mensaje") != null) {
			String Mensaje = request.getAttribute("Mensaje").toString();
	%>
	<div class="alert alert-success" role="alert">
		<%=Mensaje%>
	</div>
	<%
		}
	%>

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div></div>
				<table id="ListarCursosProfesor" class="display" style="width: 100%">
					<thead>
						<tr>
							<th>Materia</th>
							<th>Turno</th>
							<th>Cuatrimestre</th>
							<th>Año</th>
							<th>Cant. Alumnos</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>


						<%
							ArrayList<Curso> listaCurso = null;
							if (request.getAttribute("listaCursos") != null) {
								listaCurso = (ArrayList<Curso>) request.getAttribute("listaCursos");
							}
						%>
						<%
							if (listaCurso != null)
								for (Curso curso2 : listaCurso) {
						%>
						<tr>
							<td><%=curso2.getMateria()%></td>
							<td><%=curso2.getTurno()%></td>
							<td><%=curso2.getCuatrimestre()%></td>
							<td><%=curso2.getAnio()%></td>
							<td><%=curso2.getCantAlum()%></td>
							<td><a type="button"
								class="btn btn-outline-secondary btn-sm"
								href="ServletCurso?showCourse-professor=<%=curso2.getId()%>"
								data-toggle="tooltip" title="Ver Curso"><i
									class="fa fa-group"></i></a> <a type="button"
								class="btn btn-outline-success btn-sm"
								href="ServletAlumXcurso?uploadNotes=<%=curso2.getId()%>"
								data-toggle="tooltip" title="Cargar Notas"><i
									class="fa fa-pencil-square"></i></a>
						</tr>
						<%
							}
						%>


					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Modal cargar nota -->

	<div class="modal fade" id="VentanaCargaNota" data-backdrop="static"
		data-keyboard="false" tabindex="-1" role="dialog"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">Cargar Nota</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-primary">Guradar</button>
				</div>
			</div>
		</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>


</body>
</html>