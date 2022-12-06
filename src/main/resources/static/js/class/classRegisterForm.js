$(document).ready(
		function() {

			// SUBMIT FORM
			$("#classRegisterForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					className : $("#className").val(),
					classRoom : $("#classRoom").val(),



				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/class/",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(data)
					  {

		        	if (data != null)
					{

						 alert("class successfully added");
                   		 window.location = "/admin/viewClass";
					}
					else
					{
						 alert("something went wrong");
                   		 window.location = "/admin/viewClass";

					}
					},


				});

			}

		})