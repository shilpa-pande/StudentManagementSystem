$(document).ready(
		function() {

			// SUBMIT FORM
			$("#teacherRegisterForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					teacherName : $("#teacherName").val(),
					subject : $("#subject").val(),
					teacherEmail:$("#teacherEmail").val(),
					teacherPassword:$("#teacherPassword").val(),



				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/teacher/register",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(data)
					  {

		        	if (data != null)
					{

						 alert("teacher successfully added");
                   		 window.location = "/admin/viewTeacher";
					}
					else
					{
						 alert("something went wrong");
                   		 window.location = "/admin/viewTeacher";

					}
					},


				});

			}

		})