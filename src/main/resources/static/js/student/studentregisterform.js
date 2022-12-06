$(document).ready(
		function() {

			// SUBMIT FORM
			$("#studentRegisterForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					studentName : $("#studentName").val(),
					studentMobileNo : $("#studentMobileNo").val(),
					studentEmail : $("#studentEmail").val(),
					studentPassword : $("#studentPassword").val(),
					studentAddress : $("#studentAddress").val()
					
					
				}
				
				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/students/register",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(data)
					  {
				   
		        	if (data != null) 
					{
						
						 alert("student successfully added");
                   		 window.location = "/admin/viewStudent";
					}
					else 
					{
						 alert("something went wrong");
                   		 window.location = "/admin/viewStudent";
						
					}
					},
		
				
				});

			}

		})