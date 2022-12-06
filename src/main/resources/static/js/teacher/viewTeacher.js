$(document).ready(
	function() {

		// SUBMIT FORM
		$("#viewTeacher").submit(function(event) {
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
                    '<td id = "ID' + item.teacherId + '">' + item.teacherId + '</td>' +
					'<td id = "Name' + item.teacherName + '">' + item.teacherName + '</td>' +

					'<td id = "Subject' + item.subject + '">' + item.subject + '</td>' +

					'<td>' +
					'<button type = "button" id = "edit' + item.teacherId + '" class = "btn btn-warning btn-md edit">Edit</button>' +
					'</td>' +
					'<td>' +
					'<button type = "button" id = "delet' + item.teacherId + '" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.teacherId + ')">Delete</button>' +
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
		url: "/teacher/",
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
function delet(teacherId){
	if (confirm('Do you really want to delete record?')) {
		var parent = $(this).parent().parent();
		alert(teacherId);
		$.ajax({
		type: "DELETE",
			url: "/teacher/" +teacherId,
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

	var teacherId = parent.children("td:nth-child(1)");
	var teacherName = parent.children("td:nth-child(2)");
	var subject = parent.children("td:nth-child(3)");



    var buttons = parent.children("td:nth-child(4)");


	teacherName.html("<input type='text' id='teacherName' value='" + teacherName.html() + "'/>");
	subject.html("<input type='text' id='subject' value='" + subject.html() + "'/>");

	buttons.html("<button id='save' class= 'btn btn-success'>Save</button>");

});

$(document).delegate('#save', 'click', function() {
	var parent = $(this).parent().parent();

   var teacherId = parent.children("td:nth-child(1)");
   	var teacherName = parent.children("td:nth-child(2)");
   	var subject = parent.children("td:nth-child(3)");


    var buttons = parent.children("td:nth-child(4)");



	$.ajax({
		type: "PUT",
		contentType: "application/json; charset=utf-8",
		url: "/teacher/"+teacherId[0].innerText,
		data: JSON.stringify({
			'teacherId': teacherId[0].innerText, 'teacherName': teacherName.children("input[type=text]").val(),'subject':subject.children("input[type=text]").val(),


		}),
		cache: false,
		success: function() {
	    	teacherName.html(teacherName.children("input[type=text]").val());
	    	subject.html(subject.children("input[type=text]").val());


			buttons.html("<button class='btn btn-warning edit' id='" + teacherId[0].innerText + "'>Edit</button>");
		},

	});



});




