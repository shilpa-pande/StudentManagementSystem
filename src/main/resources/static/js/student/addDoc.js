$(document).ready(
	function() {
		$("#submit").submit(function(event) {
			event.preventDefault();
			 var studentId = $('#studentId').val();
                                				   if(studentId){
                                                                      //update it
                                                                      dynamicURL = "/students/uploadDoc/"+studentId;

                                                                  }
                                				  console.log(studentId);
			ajaxPost();

		});

		function ajaxPost() {
		var data = new FormData($("#submit")[0]);

			$.ajax({
				type: 'POST',
				enctype: 'multipart/form-data',
				data: data,
				url: dynamicURL,
				processData: false,
				contentType: false,
				cache: false,
				success: function()
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


