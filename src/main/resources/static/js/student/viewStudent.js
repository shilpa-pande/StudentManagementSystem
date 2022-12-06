$(document).ready(
	function() {

		// SUBMIT FORM
		$("#viewStudent").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxGet();
		});

	});
	
// DO GET
var globleTableData=[]

function decPageNo()
{
	let page=Number($('#customerPageNo').text())
	//console.log(page,'prev')
	if(page!=1)
	page-=1
	pagedata(page)
;
	$('#customerPageNo').text(page)

}

function incPageNo()
{
	let page=Number($('#customerPageNo').text())
	//console.log(page,'prev')
	pagedata(page+1);
	$('#customerPageNo').text(page+1)
}
function pagedata(pageno){
	let tableData="";
	let startno=(pageno-1)*5
	let endno=(pageno*5)
	data=globleTableData.slice(startno,endno)
	data.forEach(function(item) {
				tableData += '<tr>' +
                    '<td id = "ID' + item.studentId + '">' + item.studentId + '</td>' +
					'<td id = "Name' + item.studentName + '">' + item.studentName + '</td>' +
					'<td id = "Mobile No' + item.studentMobileNo + '">' + item.studentMobileNo + '</td>' +
					'<td id = "Email' + item.studentEmail + '">' + item.studentEmail + '</td>' +
					'<td id = "Password' + item.studentPassword + '">' + item.studentPassword + '</td>' +
					'<td id = "Address' + item.studentAddress + '">' + item.studentAddress + '</td>' +

					'<td>' +
					'<button type = "button" id = "edit' + item.studentId + '" class = "btn btn-warning btn-md edit">Edit</button>' +
					'</td>' +
					'<td>' +
					'<button type = "button" id = "delet' + item.studentId + '" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.studentId + ')">Delete</button>' +
					'</td>' +
					 
				
					'</tr>';
			});
			$("#myTable>tbody").html(tableData);
}






function ajaxGet() {
    var keyword = $("#keyword").val();
	var tableData = "";
	$.ajax({
		type: "GET",
		url: "/students/",
		data:{keyword: keyword},
		success: function(data) {
			globleTableData=data
			let page=Number($('#customerPageNo').text())
			//console.log(globleTableData,'globleTableData')
			pagedata(page)

		},
	 	
	});
}


$(document).ready(function() {
	ajaxGet();
})


//Delete
function delet(studentId){
	if (confirm('Do you really want to delete record?')) {
		var parent = $(this).parent().parent();
		alert(studentId);
		$.ajax({
		type: "DELETE",
			url: "/students/" +studentId,
			cache: false,
			success: function() {
				parent.fadeOut('slow', function() {
					$(this).remove();
				});
				location.reload(true)
			},
			error: function() {
				$('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function() {
					$(this).remove();
			});
			}
		});
	}
};


/* Edit */

$(document).delegate('.edit', 'click', function() {

	var parent = $(this).parent().parent();

	var studentId = parent.children("td:nth-child(1)");
	var studentName = parent.children("td:nth-child(2)");
	var studentMobileNo = parent.children("td:nth-child(3)");
	var studentEmail = parent.children("td:nth-child(4)");
	var studentPassword = parent.children("td:nth-child(5)");
	var studentAddress = parent.children("td:nth-child(6)");


    var buttons = parent.children("td:nth-child(7)");


	studentName.html("<input type='text' id='studentName' value='" + studentName.html() + "'/>");
	studentMobileNo.html("<input type='text' id='studentMobileNo' value='" + studentMobileNo.html() + "'/>");
	studentEmail.html("<input type='email' id='studentEmail'  value='"+ studentEmail.html() +"'/>");

	studentPassword.html("<input type='password' id='studentPassword' value='" + studentPassword.html() + "'/>");
	studentAddress.html("<input type='text' id='studentAddress' value='" + studentAddress.html() + "'/>");
	buttons.html("<button id='save' class= 'btn btn-success'>Save</button>");

});

$(document).delegate('#save', 'click', function() {
	var parent = $(this).parent().parent();

    var studentId = parent.children("td:nth-child(1)");
    console.log(studentId)
	var studentName = parent.children("td:nth-child(2)");
	var studentMobileNo = parent.children("td:nth-child(3)");
	var studentEmail = parent.children("td:nth-child(4)");
	var studentPassword = parent.children("td:nth-child(5)");
	var studentAddress = parent.children("td:nth-child(6)");


    var buttons = parent.children("td:nth-child(7)");



	$.ajax({
		type: "PUT",
		contentType: "application/json; charset=utf-8",
		url: "/students/"+studentId[0].innerText,
		data: JSON.stringify({
			'studentId': studentId[0].innerText, 'studentName': studentName.children("input[type=text]").val(),'studentMobileNo':studentMobileNo.children("input[type=text]").val(),'studentEmail':studentEmail.children("input[type=email]").val(),

			'studentPassword': studentPassword.children("input[type=password]").val(), 'studentAddress':studentAddress.children("input[type=text]").val(),
		}),
		cache: false,
		success: function() {
	    	studentName.html(studentName.children("input[type=text]").val());
	    	studentMobileNo.html(studentMobileNo.children("input[type=text]").val());
	    	studentEmail.html(studentEmail.children("input[type=email]").val());

	    	studentPassword.html(studentPassword.children("input[type = password]").val());
	    	 studentAddress.html(studentAddress.children("input[type=text]").val());

			buttons.html("<button class='btn btn-warning edit' id='" + studentId[0].innerText + "'>Edit</button>");
		},

	});



});




