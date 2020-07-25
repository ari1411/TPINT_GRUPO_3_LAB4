<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entidades.AlumnosPorCursos"%>
<%@page import="entidades.Curso"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cargar nota</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" />
</head>
<body>

	<jsp:include page="Menu.jsp"></jsp:include>
	<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">Cargar
			notas</li>
	</ol>
	</nav>

	<form action="ServletAlumXcurso" method="post" style="margin: 40px">

		<div class="container">

			<%
				Curso CursoActual = null;
				if (request.getAttribute("InfoCurso") != null) {
					CursoActual = (Curso) request.getAttribute("InfoCurso");
				}
			%>

			<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<div>
					<h1 class="display-4"><%=CursoActual.getMateria()%></h1>
					<h2 class="lead"><%=CursoActual.toStringSinMateria()%></h2>
				</div>
			</ol>
			</nav>


			<%
				int IdCurso = 0;
				if (request.getAttribute("CursoId") != null) {
					IdCurso = Integer.parseInt(request.getAttribute("CursoId").toString());
				}
			%>
			<%
				if (request.getAttribute("Mensaje") != null) {
					String Mensaje = request.getAttribute("Mensaje").toString();
			%>
			<div class="alert alert-danger" role="alert">
				<%=Mensaje%>
			</div>
			
			<%
				}
			%>
			<input type="hidden" name="txtIdCurso" id="txtIdCurso"
				value="<%=IdCurso%>">
			<div class="row">
				<div class="col-lg-12">
					<table id="TablaCargarNotas" class="display" style="width: 100%">
						<thead>
							<tr>
								<th>Legajo</th>
								<th>Alumno</th>
								<th>Parcial 1</th>
								<th>Parcial 2</th>
								<th>Recuperatorio 1</th>
								<th>Recuperatorio 2</th>
								<th>Estado academico</th>
							</tr>
						</thead>
						<tbody>



							<%
								if (request.getAttribute("listaAlumNotas") != null) {
									ArrayList<AlumnosPorCursos> NotasdeAlumnos = (ArrayList<AlumnosPorCursos>) request
											.getAttribute("listaAlumNotas");
									for (AlumnosPorCursos alumnoXNota : NotasdeAlumnos) {
							%>

							<tr>
								<td><input type="hidden" name="LegAlumno" id="LegAlumno"
									value="<%=alumnoXNota.getAlumno().getLegajo()%>"> <label
									id="LegAlumno" name="LegAlumno"><%=alumnoXNota.getAlumno().getLegajo()%></label></td>
								<td><%=alumnoXNota.getAlumno().getNombre()%></td>
								<td>
									<div class="form-group">

										<%
											if (alumnoXNota.getParcial1() == 0) {
										%>
										<input type="number" max="10" min="1" class="form-control"
											id="notaParcial1" name="notaParcial1" placeholder="Calificar">
										<%
											} else {
										%>
										<input type="number" max="10" min="1" class="form-control"
											id="notaParcial1" name="notaParcial1" placeholder="Calificar"
											value="<%=alumnoXNota.getParcial1()%>">
										<%
											}
										%>

									</div>
								</td>
								<td>
									<div class="form-group">
										<%
											if (alumnoXNota.getParcial2() == 0) {
										%>
										<input type="number" max="10" min="1" class="form-control"
											id="notaParcial2" name="notaParcial2" placeholder="Calificar">
										<%
											} else {
										%>
										<input type="number" max="10" min="1" class="form-control"
											id="notaParcial2" name="notaParcial2" placeholder="Calificar"
											value="<%=alumnoXNota.getParcial2()%>">
										<%
											}
										%>
									</div>
								</td>
								<td>
									<div class="form-group">
										<%
											if (alumnoXNota.getRecuperatorio1() == 0) {
										%>
										<input type="number" max="10" min="1" class="form-control"
											id="Recuperatorio1" name="Recuperatorio1"
											placeholder="Calificar">
										<%
											} else {
										%>
										<input type="number" max="10" min="1" class="form-control"
											id="Recuperatorio1" name="Recuperatorio1"
											placeholder="Calificar"
											value="<%=alumnoXNota.getRecuperatorio1()%>">
										<%
											}
										%>
									</div>
								</td>
								<td>
									<div class="form-group">
										<%
											if (alumnoXNota.getRecuperatorio2() == 0) {
										%>
										<input type="number" max="10" min="1" class="form-control"
											id="Recuperatorio2" name="Recuperatorio2"
											placeholder="Calificar">
										<%
											} else {
										%>
										<input type="number" max="10" min="1" class="form-control"
											id="Recuperatorio2" name="Recuperatorio2"
											placeholder="Calificar"
											value="<%=alumnoXNota.getRecuperatorio2()%>">
										<%
											}
										%>
									</div>
								</td>
								<td>
									<div class="form-group">
										<select name="cmbEstadoAc" id="cmbEstadoAc"
											class="custom-select">
											<option selected disabled value="">Seleccione..</option>
											<%
												if (alumnoXNota.getEstadoAca().getId() == 1) {
											%><option value="1" Selected>Libre</option>
											<%
												} else {
											%><option value="1">Libre</option>
											<%
												}
											%>
											<%
												if (alumnoXNota.getEstadoAca().getId() == 2) {
											%><option value="2" Selected>Cursando</option>
											<%
												} else {
											%><option value="2">Cursando</option>
											<%
												}
											%>
											<%
												if (alumnoXNota.getEstadoAca().getId() == 3) {
											%><option value="3" Selected>Regular</option>
											<%
												} else {
											%><option value="3">Regular</option>
											<%
												}
											%>
											<%
												if (alumnoXNota.getEstadoAca().getId() == 4) {
											%><option value="4" Selected>Promocionado</option>
											<%
												} else {
											%><option value="4">Promocionado</option>
											<%
												}
											%>

										</select>
									</div>
								</td>
							</tr>


							<%
								}
							%>

							<%
								}
							%>


						</tbody>
						<tfoot>
							<tr>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</tfoot>
					</table>


					<button id="btnActualizarNotas" name="btnActualizarNotas"
						class="btn btn-primary" type="submit">Guardar Notas</button>
					<a id="Retroceder" name="Retroceder" class="btn btn-secondary"
						type="submit" href="ServletCurso?listCoursesProfessor=0">Volver</a>
				</div>
			</div>
		</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
		<script type="text/javascript" src="js/script.js"></script>
	</form>
</body>
</html>