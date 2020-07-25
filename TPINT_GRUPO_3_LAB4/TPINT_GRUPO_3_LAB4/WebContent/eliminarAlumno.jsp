<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eliminar Alumno</title>
</head>
<body>
<jsp:include page="Menu.jsp"></jsp:include>

	<form style="margin: 40px">
		<div class="form-row">
			<div class="col-md-3 mb-3">
				<label for="validationServer01">Nombre</label> <input type="text"
					class="form-control" id="validationServer01" disabled=true value="Ariel">
				<div class="valid-feedback">Looks good!</div>
			</div>
			<div class="col-md-3 mb-3">
				<label for="validationServer02">Apellido</label> <input type="text"
					class="form-control " id="validationServer02" disabled=true value="Lobos">
				<div class="valid-feedback">Looks good!</div>
			</div>
			<div class="col-md-2 mb-3">
				<label for="validationServer03">DNI</label> <input type="text"
					class="form-control " id="validationServer02" disabled=true value="30006789">
				<div class="valid-feedback">Looks good!</div>

			</div>
		</div>
		<div class="form-row">
			<div class="col-md-2 mb-3">
				<label for="validationServer01">Fecha de Nacimiento</label> <input
					type="text" class="form-control" id="validationServer01" disabled=true value="14/11/1990">
				<div class="valid-feedback">Looks good!</div>
			</div>
			<div class="col-md-3 mb-3">
				<label for="validationServer02">Direccion</label> <input type="text"
					class="form-control " id="validationServer02" disabled=true value="Lanzareto 897">
				<div class="valid-feedback">Looks good!</div>
				</div>
				<div class="col-md-3 mb-3">
					<label for="validationServer03">Localidad</label> <input
						type="text" class="form-control " id="validationServer03" disabled=true value="Talar">
					<div class="invalid-feedback">Please provide a valid city.</div>
				</div>
				<div class="col-md-3 mb-3">
					<label for="validationServer04">Provincia</label> <select
						class="custom-select " id="validationServer04" disabled=true value="Buenos Aires">
						<option selected disabled value="">Choose...</option>
						<option>Buenos Aires</option>
					</select>
					<div class="invalid-feedback">Please select a valid state.</div>
				</div>
				<!-- <div class="col-md-3 mb-3">
					<label for="validationServer05">C.P</label> <input type="text"
						class="form-control " id="validationServer05" required>
					<div class="invalid-feedback">Please provide a valid zip.</div>
				</div> -->
			</div>
			
			<div class="form-row">
			<div class="col-md-6 mb-3">
				<label for="validationServer01">Telefono</label> <input
					type="text" class="form-control" id="validationServer01" disabled=true value="46670291">
				<div class="valid-feedback">Looks good!</div>
			</div>
			<div class="col-md-6 mb-3">
				<label for="validationServer01">Email</label> <input
					type="text" class="form-control" id="validationServer01" disabled=true value="arilobos@gmail.com">
				<div class="valid-feedback">Looks good!</div>
			</div>
			 </div>
					
			<button class="btn btn-primary" type="submit">Modificar</button>
	</form>


</body>
</html>