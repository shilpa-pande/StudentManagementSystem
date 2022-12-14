$(document).ready(
		function() {

			// SUBMIT FORM
			$("#addAttendance").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();



				ajaxPost();
			});

			function ajaxPost() {
             var studentId = $('#studentId').val();
				// PREPARE FORM DATA
				var formData = {
					date : $("#date").val(),
					attendance : $("#attendance").val(),




				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/attendance/"+studentId,
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(data)
					  {

		        	if (data != null)
					{

						 alert("attendance successfully added");
                   		 window.location = "/teacherView/viewClass";
					}
					else
					{
						 alert("something went wrong");
                   		 window.location = "/teacherView/viewClass";

					}
					},


				});

			}

		})