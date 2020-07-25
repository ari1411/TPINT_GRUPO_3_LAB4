<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Usuarios</title>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>
	<nav aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">Usuarios</li>
	</ol>
	</nav>

	<div class="col-md-4 offset-md-4">
		<a href="ServletUsuarios?AddUser=1" type="button"
			class="btn btn-outline-success btn-block"><i
			class="fa fa-user-circle-o"></i> Agregar Usuario</a>
	</div>



	<div class="container">
		
		<%if (request.getAttribute("cantFilas") != null) {%>
		
		<div class="alert alert-success" role="alert">Usuario Agregado Correctamente</div>
		
		<%}%>
			
	
		<%if (request.getAttribute("contraseñaU") != null) {%>
				
			<div class="alert alert-warning" role="alert">La clave se modifico correctamente</div>
      
        <%}%>
		
		
		<%if (request.getAttribute("EliminadoUsuario") != null) {%>
		
		<div class="alert alert-danger" role="alert">Se elimino correctamente</div>
		
		<%}	%>

		<%ArrayList<Usuario> listaUsuario = null;
			if (request.getAttribute("listaUsuarios") != null) {
				listaUsuario = (ArrayList<Usuario>) request.getAttribute("listaUsuarios");
			}%>

		<div class="row">
			<div class="col-lg-12">
				
				<table id="example" class="display" style="width: 100%">
					<thead>
						<tr>
							<th>ID</th>
							<th>Usuario</th>
							<th>Legajo</th>
							<th>Tipo</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<%
							int IdUsuarioActual = 0;
							IdUsuarioActual = Integer.parseInt(session.getAttribute("Session_Id").toString());
							if (listaUsuario != null)
								for (Usuario user : listaUsuario) {
						%>
						<tr>
							<td><%=user.getId()%> <input type="hidden" name="idUsuario"
								value="<%=user.getId()%>"></td>
							<td><%=user.getUsername()%></td>
							<td><%=user.getLegajo()%></td>
							<td><%=user.getTipoUsuario().getTipo()%></td>
							<td><button type="button" class="btn btn-info btn-sm"
									onClick="modalCambiarCon(this)" id="<%=user.getId()%>"
									data-toggle="tooltip" data-placement="top"
									title="Cambiar Clave">
									<i class="fa fa-refresh"></i>
								</button>
								 <%	if (user.getId() != 1 && user.getId() != IdUsuarioActual) { %>
								<button class="btn btn-danger btn-sm" type="button"
									onClick="modalEliminar(this)" id="<%=user.getId()%>"
									name="btn-EliminarAlumno"
									data-toggle="tooltip" data-placement="top" title="Eliminar">
									<i class="fa fa-trash"></i>
								</button> <%} else { %>
								<button class="btn btn-outline-danger btn-sm" type="button"
									onClick="modalEliminar(this)" id="<%=user.getId()%>"
									name="<%=user.getUsername()%>"
									data-toggle="tooltip" data-placement="top" title="Eliminar"
									disabled=true>
									<i class="fa fa-trash"></i>
								</button> <%} %></td>
						</tr>
						<!--MODAL ELIMINAR-->
						<div class="modal fade" id="VentEliminar" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Eliminar Usuario</h5>
										<button class="close" type="button" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">X</span>
										</button>
									</div>
									<div class="modal-body">Esta seguro que desea eliminar el
										Usuario?</div>
									<div class="modal-footer">
										<button class="btn btn-secondary" type="button"
											data-dismiss="modal">Cancel</button>
										<a class="btn btn-danger test" id="LegajoEliminar" href="">Eliminar</a>

									</div>
								</div>
							</div>
						</div>
						<!--MODAL CAMBIAR CONTRASEÑA-->

						<div class="modal fade" id="VentCambiarContras" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<form action="ServletUsuarios" method="post">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Cambiar
												Clave</h5>
											<button class="close" type="button" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">X</span>
											</button>
										</div>
										<div class="modal-body">

                      
											<label for="recipient-name" class="col-form-label">Ingrese la nueva clave : </label> 
												
													<input type="password" class="form-control"
												id="contraseñaModificar" name="contraseñaModificar" required="">
												<br>
													<button id="show_password" class="btn btn-primary"
										type="button" onclick="mostrarPassword()">
										<span class="fa fa-eye-slash icon"></span>
									</button>
													
												<div class="input-group-append">
										
											<input type="hidden" class="form-control" id="iduserAModificar"
												name="iduserAModificar">
												</div>
											
										
                      
										</div>
										<div class="modal-footer">
											<button class="btn btn-secondary" type="button"
												data-dismiss="modal">Cancel</button>
											<button class="btn btn-primary test" id="BtnLegajo"
												name="BtnLegajo" type="submit">Guardar</button>
										</div>
									</form>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
<!-- <script type="text/javascript" src="js/script.js"></script> -->
<script src="js/espanol.js"></script>	

	<script type="text/javascript">
		function modalEliminar(btn) {
			var Legajo = btn.id;
			var hr = "ServletUsuarios?deleteUser=" + Legajo;
			jQuery.noConflict();
			$('#VentEliminar').modal('show');
			var enlace = document.querySelector('.test');
			enlace.href = hr;
		}
		function modalCambiarCon(btn) {
			var IdUsser = btn.id;
			jQuery.noConflict();
			$('#VentCambiarContras').modal('show');
			/* $('#VentCambiarContras').find('.modal-title').text(IdUsser);  ussername */
			var inputUsser = document.getElementById("iduserAModificar");
			inputUsser.value = IdUsser;
		
		}
		function mostrarPassword(){
			var cambio = document.getElementById("contraseñaModificar");
			if(cambio.type == "password"){
				cambio.type = "text";
				$('.icon').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
			} else {
				cambio.type = "password";
				$('.icon').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
			}
		}
	</script>
</body>
</html>