<%@page import="entidades.Alumno"%>
<%@page import="entidades.Localidad"%>
<%@page import="daoImpl.AlumnoDaoImpl"%>
<%@page import="daoImpl.MateriaDaoImpl"%>
<%@page import="entidades.Materia"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de alumnos</title>

<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<style>
.bs-example {margin: 20px;}
td{padding: 1px 2px!important;font-size: 14px;text-align:center;} 
th {font-size: 14px;text-align:center;}

</style>

</head>
<body>

	<jsp:include page="Menu.jsp"></jsp:include>

	<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">Alumnos</li>
	</ol>
	</nav>

	<div class="container" style="max-width: 90% !important;">

		<form action="ServletAlumno?Param=Filtrar" method="get">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
			<div class="row">
				<div class="col-lg-6">
					<div class="form-group">
						<label for="sel1">Materias:</label> <select id="cbxMateria"
							name="cbxMateria" class="custom-select" id="sel1">
							<option selected disabled value="<>">Seleccione...</option>
							<%
								MateriaDaoImpl materiaL = new MateriaDaoImpl();
							ArrayList<Materia> listaMateria = null;
							listaMateria = (ArrayList<Materia>) materiaL.listarMaterias();
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
				</div>
				<div class="col-lg-3">
					<div class="form-group">
						<label for="sel1">Cuatrimestre:</label> <select
							id="cbxCuatrimestre" name="cbxCuatrimestre" class="form-control"
							id="sel1">
							<option selected disabled value="">Seleccione...</option>
							<option value="1">1� Cuatrimestre</option>
							<option value="2">2� Cuatrimestre</option>

						</select>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">

						<label for="sel1">A�o:</label> <select id="cdxAnio" name="cdxAnio"
							class="form-control" id="sel1">
							<option selected disabled value="">Seleccione...</option>
							<%
								for (int x = 2020; x >= 1990; x--) {
							%>
							<option><%=x%></option>
							<%
								}
							%>
						</select>

					</div>

				</div>
				
			</div>
			<div class="col align-self-center">
					<button id="btn-filtrar" name="btn-filtrar" class="btn btn-outline-info mt-3"
						type="submit"><i class="fa fa-search"></i>Filtrar</button>
					
				</div>
				</ol>
			</nav>
		</form>
		
		<div class="col-md-4 offset-md-4">
     <a href="ServletAlumno?BtnAgregar=Alumno" type="button" class="btn btn-outline-success btn-block "><i class="fa fa-user-plus "></i> Agregar Alumno</a>
    </div>
			

		<div class="table-responsive">
			<div class="col-sm-12 col-md-6">
				<div class="dataTables_length" id="example_length"></div>
				<div class="col-sm-12 col-md-6">
					<div id="example_filter" class="dataTables_filter">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 ">
					<br>
					<%if (request.getAttribute("cantFilas") != null) { %>
					<div class="alert alert-success" role="alert">Se agrego correctamente</div>
<%} %>

				<%if (request.getAttribute("AlumnoEliminado") != null) { %>
				<div class="alert alert-danger" role="alert">Se elimino correctamente</div>
				
				<%} %>
				
				<% if(request.getAttribute("cantFilasmod")!=null){ %>
				<div class="alert alert-warning" role="alert">Se modifico correctamente</div>
				
				
				<%} %>
					<table id="example" class="display" style="width: 100%">
						<thead>
							<tr>
								<th>Legajo</th>
								<th>Nombre</th>
								<th>Apellido</th>
								<th>DNI</th>
								<th>Nacimiento</th>
								<th>Direccion</th>
								<th>Localidad</th>
								<th>Provincia</th>
								<th>Telefono</th>
								<th>Mail</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<%
							ArrayList<Alumno> listaAlumno = null;
							int cont=0;
							int id=(int)session.getAttribute("Session_Legajo");
							if(id!=0 && id!=1)
							{								
								AlumnoDaoImpl AlumnoXPro=new AlumnoDaoImpl();
								listaAlumno=AlumnoXPro.filtroProProfesor(id);
								cont++;
							}
								
							if (cont==0 && id==1) {
								listaAlumno = (ArrayList<Alumno>) request.getAttribute("listaAlum");
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
								<td>
								<a href="ServletAlumno?Param=ModificarAlumno&amp;Data=<%=alumno.getLegajo()%>" type="button" name="btn-EditarAlumno" class="btn btn-outline-secondary btn-sm" data-toggle="tooltip" title="Editar">
								    <i class="fa fa-edit">
								    </i>
								  </a>
								  <button type="button" onClick="modalEliminar(this)" id="<%=alumno.getLegajo()%>"
									name="btn-EliminarAlumno" class="btn btn-outline-danger btn-sm" data-toggle="tooltip" title="Eliminar">
									<i class="fa fa-trash-o"></i> 
									</button></td>
							</tr>
							<div class="modal fade" id="VentEliminar" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
											<button class="close" type="button" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">X</span>
											</button>
										</div>
										<div class="modal-body">Esta seguro que desea eliminar
											el registro?</div>
										<div class="modal-footer">
											<button class="btn btn-secondary" type="button"
												data-dismiss="modal">Cancel</button>
											<a class="btn btn-danger test" id="LegajoEliminar"
												href="">Eliminar</a>
												
										</div>
									</div>
								</div>
							</div>
							
							
							<%
								}
							%>
						</tbody>
					</table>


				</div>
			</div>
		</div>
</div>
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<script src="js/espanol.js"></script>		
<script type="text/javascript">
function modalEliminar(btn){
	var LegajoAlumno = btn.id;
	var hr = "ServletAlumno?Param=EliminarAlumno&Data="+LegajoAlumno;
	jQuery.noConflict();
	$('#VentEliminar').modal('show');
	var enlace = document.querySelector('.test');
	enlace.href = hr;
	
}
</script>	
	

</body>
</html>
