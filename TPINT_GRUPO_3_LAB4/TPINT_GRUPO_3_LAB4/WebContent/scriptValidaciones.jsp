 <style>
  #lblCreado{color: #4F8A10!important;background: #DFF2BF!important;margin:10px 22px;font-size:14px;vertical-align:middle;}
  #lblError{color: #D8000C!important;background-color: #FFD2D2!important;margin:10px 22px;font-size:14px;vertical-align:middle;}
  #txtCaracteres{color:red;}
</style>

<script>
function cambiar_Localidad(){ 
	var ProvinciaId;
	ProvinciaId = document.getElementById('cmbProvincia').value;
	$.ajax({
		type : 'POST',
		url : 'ServletsLocalidad',
	    dataType : "json", 
		data : {
			Provinciaid : ProvinciaId
		},
		success : function(result) {
			console.log(result);
			 if (result) {
				$("#cmbLocalidad option:not(:disabled)").remove();
				$.each(result, function(index, option) {
					console.log("option: " + option)
					$("#cmbLocalidad").append(
							'<option value="' + option.Id + '">'
									+ option.Nombre + '</option>')					
				}); 
			}
		},
		error : function(data) {
			alert('fail');
		}
	})
};   

function onlyLetter(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letter = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    special = "8-37-39-46";
    special_key = false
    for (var i in special) {
        if (key == special[i]) {
            special_key = true;
            break;
        }
    }
    if (letter.indexOf(tecla) == -1 && !special_key) {
        return false;
    }
}

function onlyNumber(car) {
    var key = window.Event ? car.which : car.keyCode;
    return (key >= 48 && key <= 57)
}

function validateMin() {
    var Min_Length = 8;
    var length = $("#txtTelefono").val().length;
    $("#txtCaracteres").remove();
    if (length < Min_Length)
    {
        $("#txtTelefono").addClass("is-invalid");
        $("#txtTelefono").after("<p id='txtCaracteres'>La cantidad de caracteres es 8 o 10, usted escribio " + length + " caracteres</p>");
        return false;
    }
    else{$("#txtTelefono").addClass("is-valid");}
}

function validateDni() {
    var Min_Length = 8;
    var length = $("#txtDni").val().length;
    if (length < Min_Length)
    {
        $("#txtDni").addClass("is-invalid");
        return false;
    }

    else{
    $("#txtDni").addClass("is-valid");
    $("#txtDni").removeClass("is-invalid");}
}

function validateNombre() {
    var Min_Length = 1;
    var length = $("#txtNombre").val().length;
    if (length < Min_Length)
    {
        $("#txtNombre").addClass("is-invalid");
        return false;
    }else{
    $("#txtNombre").addClass("is-valid");
    $("#txtNombre").removeClass("is-invalid");}
}

function validateApellido() {
    var Min_Length = 1;
    var length = $("#txtApellido").val().length;
    if (length < Min_Length)
    {
        $("#txtApellido").addClass("is-invalid");
        return false;
    }else{
    $("#txtApellido").addClass("is-valid");
    $("#txtApellido").removeClass("is-invalid");}
}
function validateFechaNac() {
    var Min_Length = 1;
    var length = $("#txtFechaNac").val().length;
    if (length < Min_Length)
    {
        $("#txtFechaNac").addClass("is-invalid");
        return false;
    }else{
    $("#txtFechaNac").addClass("is-valid");
    $("#txtFechaNac").removeClass("is-invalid");}
}
function validateDireccion() {
    var Min_Length = 1;
    var length = $("#txtDireccion").val().length;
    if (length < Min_Length)
    {
        $("#txtDireccion").addClass("is-invalid");
        return false;
    }else{
    $("#txtDireccion").addClass("is-valid");
    $("#txtDireccion").removeClass("is-invalid");}
}
function validateProvincia() {
    var Min_Length = 1;
    var length = $("#cmbProvincia").val().length;
    if (length < Min_Length)
    {
        $("#cmbProvincia").addClass("is-invalid");
        return false;
    }else{
    $("#cmbProvincia").addClass("is-valid");
    $("#cmbProvincia").removeClass("is-invalid");}
}

function validateLocalidad() {
    var Min_Length = 1;
    var length = $("#cmbLocalidad").val().length;
    if (length < Min_Length)
    {
        $("#cmbLocalidad").addClass("is-invalid");
        return false;
    }else{
    $("#cmbLocalidad").addClass("is-valid");
    $("#cmbLocalidad").removeClass("is-invalid");}
}

function cleanError() {
    $("#txtCaracteres").remove();
    $("#txtTelefono").removeClass("is-invalid");
    //$("#txtTelefono").addClass("is-valid");
}

function validateMail() {
    obj = document.getElementById("txtEmail");
    valueForm = obj.value;
    var mailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
    if (valueForm.search(mailFormat) == 0) {
    	$("#txtEmail").addClass("is-valid");
    	$("#txtEmail").removeClass("is-invalid");
    } else {
    	$("#txtEmail").addClass("is-invalid");
    }
}


</script>