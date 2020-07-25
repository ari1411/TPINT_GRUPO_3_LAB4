<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Provincia" %>
<%@page import="entidades.Localidad" %>
<%@page import="entidades.Alumno" %>
<%@page import="daoImpl.AlumnoDaoImpl" %>
<%@page import="daoImpl.LocalidadDaoImpl" %>
<%@page import="daoImpl.ProvinciaDaoImpl" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.time.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alumno</title>
<style>
 #lblError{color: #D8000C!important;background: #FFD2D2!important;margin:10px 22px;font-size:16px;vertical-align:middle;}
</style>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>
	  

			 <%  Alumno alum=null;
  		         if(request.getAttribute("AlumnoRep")!=null){
		         alum=(Alumno)request.getAttribute("AlumnoRep");%>	
		         
		<nav aria-label="breadcrumb"> 		
		<ol class="breadcrumb"> 			
		<li class="breadcrumb-item active" aria-current="page">Agregar Alumno</li> 
		</ol> 		
		</nav>
	
		<%} %>
		
		  <%  if(request.getAttribute("AlumnoRepModi")!=null){ 
		  alum=(Alumno)request.getAttribute("AlumnoRepModi");%>  
		  <nav aria-label="breadcrumb"> 		
		<ol class="breadcrumb"> 			
		<li class="breadcrumb-item active" aria-current="page">Modificar Alumno</li> 
		</ol> 		
		</nav>
		
		<%} %>     
		         	         
	<form action="ServletAlumno" method="get" style="margin: 40px">
	<div class="alert alert-danger" role="alert">(*)DNI ya registrado</div>
	<input value="<%=alum.getLegajo()%>" name="txtlegajo" type="hidden" class="form-control">
		<div class="form-row">
			<div class="col-md-3 mb-3">
				<label for="txtNombre">Nombre</label> 
				<input value="<%=alum.getNombre() %>" name="txtNombre" type="text" class="form-control" id="txtNombre" onFocusOut="return validateNombre()" required>
			</div>
			<div class="col-md-3 mb-3">
				<label for="txtApellido">Apellido</label> 
				<input  value="<%=alum.getApellido() %>" name="txtApellido" type="text" class="form-control " id="txtApellido" onFocusOut="return validateApellido()" required>
			</div>
			<div class="col-md-2 mb-3">
				<label for="txtDni">DNI</label> 
				<input  value="<%=alum.getDni() %>" name="txtDni" type="text"  class="form-control is-invalid" id="txtDni" onFocusOut="return validateDni()" required>
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
				<input  value="<%=alum.getFechaNac() %>" name="txtFechaNac" max="<%=dmyFormat.format(myDate)%>" type="date" class="form-control" id="txtFechaNac" onFocusOut="return validateFechaNac()" required>
			</div>
			<div class="col-md-3 mb-3">
				<label for="txtTelefono">Telefono</label> 
				<input value="<%=alum.getTelefono() %>" name="txtTelefono" type="text" class="form-control" id="txtTelefono" onFocusOut="return validateMin()" required>
			</div>
			<div class="col-md-3 mb-3">
				<label for="txtEmail">Email</label> 
				<input value="<%=alum.getMail() %>" name="txtEmail" type="text" class="form-control" id="txtEmail" onFocusOut="return validateMail()" required>
			</div>
       </div>
       <br>
		<div class="form-row">
			<div class="col-md-3 mb-3">
				<label for="txtDireccion">Direccion</label>  
				<input value="<%=alum.getDireccion() %>" name="txtDireccion" type="text" class="form-control " id="txtDireccion" onFocusOut="return validateDireccion()" required>
				<div class="valid-feedback">Looks good!</div>
				</div>
				<div class="col-md-3 mb-3">
					<label for="cmbProvincia">Provincia</label> 
					<select name="cmbProvincia" class="custom-select " id="cmbProvincia" onchange="return cambiar_Localidad()" onFocusOut="return validateProvincia()">
						<option selected disabled value="">Provincia</option>
						<%
						  ArrayList<Provincia>ListarProvi=null;
							if(request.getAttribute("listaProvDao")!=null){
							ListarProvi = (ArrayList<Provincia>) request.getAttribute("listaProvDao");}%>
							<% if(ListarProvi!=null)
								for(Provincia prov : ListarProvi) {%>
								<%if(alum.getLocalidad().getProvincia().getId()==prov.getId()){%>
								<option selected value="<%=prov.getId()%>"><%=prov.getNombreProv()%></option>
								<%} else { %>	
								<option value="<%=prov.getId()%>"><%=prov.getNombreProv() %></option>								
							<%} } %>	
					</select>
				</div>
				 <div class="col-md-2 mb-3">
					<label for="cmbLocalidad">Localidad</label> 
					<select name="cmbLocalidad" class="custom-select " id="cmbLocalidad" onFocusOut="return validateLocalidad()" required>
						<option selected disabled value="">Localidad</option>
					<%
						LocalidadDaoImpl locDaoImpl = new LocalidadDaoImpl();
						ArrayList<Localidad> listaLocalidad = null;
					      if (request.getAttribute("listaLocDao") != null) {
                          listaLocalidad = (ArrayList<Localidad>)locDaoImpl.ObtenerLocalidadPorProvincia(alum.getLocalidad().getProvincia().getId());
					}%>
					<%if (listaLocalidad != null)
						for (Localidad loc : listaLocalidad) {%>
						<%if(alum.getLocalidad().getId()==loc.getId()){%>
								<option selected  value="<%=loc.getId()%>"><%=loc.getNombreLoc()%></option>
								<%}else {  %>	
					<option value=<%=loc.getId()%>><%=loc.getNombreLoc()%></option>
					<%}}%>
					</select>
				</div>
			</div>
			<br>
			<%if(request.getAttribute("AlumnoRep")!=null){ %>
			<button id="btn-aceptar" name="btn-aceptar" class="btn btn-primary" type="submit">Aceptar</button>
			<a Id="Retroceder" name="Retroceder" class="btn btn-secondary" type="submit" href="ServletAlumno?Param=MenuAlumno">Volver</a>
			<%} %>
			
			<%if(request.getAttribute("AlumnoRepModi")!=null){ %>
			<button id="btn-EditarAlumno" name="btn-EditarAlumno" class="btn btn-primary" type="submit">Aceptar</button>
			<a Id="Retroceder" name="Retroceder" class="btn btn-secondary" type="submit" href="ServletAlumno?Param=MenuAlumno">Volver</a>
			<%} %>

	</form>
					
<jsp:include page="librerias.jsp"></jsp:include>
<jsp:include page="scriptValidaciones.jsp"></jsp:include>
</body>
</html>