$(document).ready(
		function() {

			// SUBMIT FORM
			$("#addAttendance").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					date : $("#date").val(),
					attendance : $("#attendance").val(),




				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/attendance/register",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(data)
					  {

		        	if (data != null)
					{

						 alert("attendance successfully added");
                   		 window.location = "/teacher/addAttendance";
					}
					else
					{
						 alert("something went wrong");
                   		 window.location = "/teacher/addAttendance";

					}
					},


				});

			}

		})