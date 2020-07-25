/**
 * 
 */

$(document).ready(
		function() {
			$('#example').DataTable({
				"scrollX" : false
			}), $('#AlumnosCursoAM').DataTable({
				"scrollX" : true,
				"displayLength" : 100,
				"order" : [ [ 1, "asc" ] ],
				columnDefs : [ {
					targets : "_all",
					className : 'dt-center'
				} ]
			}), $('#AlumnosCursoD').DataTable({
				"scrollX" : true,
				"displayLength" : 100,
				"order" : [ [ 0, "asc" ] ],
				columnDefs : [ {
					targets : "_all",
					className : 'dt-center'
				} ]
			}), $('#ListarCursos').DataTable(
					{
						"scrollX" : true,
						"displayLength" : 10,
						"order" : [ [ 1, "asc" ], [ 4, "desc" ], [ 3, "desc" ],
								[ 2, "asc" ], [ 0, "asc" ], [ 5, "asc" ] ],
						columnDefs : [ {
							targets : 0,
							className : 'dt-left'
						}, {
							targets : "_all",
							className : 'dt-center'
						} ]
					}), $('#ListarCursosProfesor').DataTable(
					{
						"scrollX" : true,
						"displayLength" : 10,
						"order" : [ [ 3, "desc" ], [ 2, "desc" ], [ 1, "asc" ],
								[ 0, "asc" ], [ 4, "asc" ] ],
						columnDefs : [ {
							targets : "_all",
							className : 'dt-center'
						} ]
					}), $('#TablaCargarNotas').DataTable({
				"scrollX" : false,
				"displayLength" : 100,
				"order" : [ [ 0, "asc" ] ],
				columnDefs : [ {
					targets : 1,
					className : 'dt-left'
				}, {
					targets : "_all",
					className : 'dt-center'
				} ]
			});
		});