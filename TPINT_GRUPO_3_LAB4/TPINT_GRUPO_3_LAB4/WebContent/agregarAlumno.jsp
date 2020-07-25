<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Provincia" %>
<%@page import="entidades.Localidad" %>
<%@page import="entidades.Alumno" %>
<%@page import="entidades.Usuario" %>
<%@page import="daoImpl.AlumnoDaoImpl" %>
<%@page import="daoImpl.LocalidadDaoImpl" %>
<%@page import="dao.LocalidadDao" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.time.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alumno</title>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>
	  <nav aria-label="breadcrumb"> 		
		<ol class="breadcrumb"> 			
		<li class="breadcrumb-item active" aria-current="page">Agregar Alumno</li> 
		</ol> 		
	</nav>
		         
	<form action="ServletAlumno" method="get" style="margin: 40px">
		<div class="form-row">
			<div class="col-md-3 mb-3">
				<label for="txtNombre">Nombre</label> 
				<input id="txtNombre" name="txtNombre" type="text" class="form-control" onKeyPress="return onlyLetter(event)" onFocusOut="return validateNombre()" required>
			</div>
			<div class="col-md-3 mb-3">
				<label for="txtApellido">Apellido</label> 
				<input id="txtApellido" name="txtApellido" type="text" class="form-control" onKeyPress="return onlyLetter(event)" onFocusOut="return validateApellido()" required>
			</div>
			<div class="col-md-2 mb-3">
				<label for="txtDni">DNI</label> 
				<input id="txtDni" name="txtDni" type="text" maxlength="10" class="form-control" onKeyPress="return onlyNumber(event)" onFocusOut="return validateDni()" onKeyUp="return cleanError()" required>
			</div>
		</div>
		<br>
		<div class="form-row">			
			<div class="col-md-2 mb-3">
				<label for="txtFechaNac">Fecha de Nacimiento</label> 
				<%
				Date myDate = new Date();
				SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");				
				%>
				<input name="txtFechaNac"  class="form-control" type="date" id="txtFechaNac" max="<%=dmyFormat.format(myDate)%>" onFocusOut="return validateFechaNac()" required>
			</div>
			<div class="col-md-3 mb-3">
				<label for="txtTelefono">Telefono</label> 
				<input id="txtTelefono" name="txtTelefono" type="text" class="form-control" maxlength="10" onKeyPress="return onlyNumber(event)" onFocusOut="return validateMin()" onKeyUp="return cleanError()" required>
			</div>
			<div class="col-md-3 mb-3">
				<label for="txtEmail">Email</label> 
				<input id="txtEmail" name="txtEmail" type="email" class="form-control" onFocusOut="return validateMail()" required>
			</div>
       </div>
       <br>
		<div class="form-row">
			<div class="col-md-3 mb-3">
				<label for="txtDireccion">Direccion</label>  
				<input name="txtDireccion" type="text" class="form-control " id="txtDireccion" onFocusOut="return validateDireccion()" required>
				</div>
				<div class="col-md-3 mb-3">
					<label for="cmbProvincia">Provincia</label> 
					<select name="cmbProvincia" class="custom-select " id="cmbProvincia" onchange="return cambiar_Localidad()" onFocusOut="return validateProvincia()" required>
						<option selected disabled value="">Provincia</option>
						<%
						  ArrayList<Provincia>ListarProvi=null;
							if(request.getAttribute("listaProvDao")!=null){
							ListarProvi = (ArrayList<Provincia>) request.getAttribute("listaProvDao");}%>
							<% if(ListarProvi!=null)
								for(Provincia prov : ListarProvi){%>
								<option value=<%=prov.getId()%>><%=prov.getNombreProv() %></option>
								<%} %>	
					</select>
				</div>
				 <div class="col-md-2 mb-3">
					<label for="cmbLocalidad">Localidad</label> 
					<select name="cmbLocalidad" class="custom-select " id="cmbLocalidad" onFocusOut="return validateLocalidad()" required>
						<option selected disabled value="">Localidad</option>
						<%
						  ArrayList<Localidad> listaLocalidad = null;
						  LocalidadDaoImpl LocDaoImpl = new LocalidadDaoImpl();
					      if (request.getAttribute("listaLocDao") != null) {
						  listaLocalidad = (ArrayList<Localidad>) request.getAttribute("listaLocDao");
					}%>
					<%if (listaLocalidad != null)
						for (Localidad loc : listaLocalidad) {%>
					<option value=<%=loc.getId()%>><%=loc.getNombreLoc()%></option>
					<%}%>
					</select>
				</div>
			</div>
			<br>
			<button id="btn-aceptar" name="btn-aceptar" class="btn btn-primary" type="submit">Agregar</button>
			<a id="Retroceder" name="Retroceder" class="btn btn-outline-secondary" type="submit" href="ServletAlumno?Param=MenuAlumno">Volver</a>
	</form>
	
<jsp:include page="librerias.jsp"></jsp:include>
<jsp:include page="scriptValidaciones.jsp"></jsp:include>

</body>
</html>