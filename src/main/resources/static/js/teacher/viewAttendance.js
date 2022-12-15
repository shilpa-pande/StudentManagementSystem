$(document).ready(
	function() {

		// SUBMIT FORM
		$("#teacherViewAttendance").submit(function(event) {
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
                    '<td id = "ID' + item.attendanceId + '">' + item.attendanceId + '</td>' +
					'<td id = "Name' + item.date + '">' + item.date + '</td>' +
					'<td id = "Mobile No' + item.attendance + '">' + item.attendance + '</td>' +
						'<td>' +
					'<button type = "button" id = "edit' + item.attendanceId + '" class = "btn btn-warning btn-md edit">Edit</button>' +
                    					'</td>' +
                    					'<td>' +
                    					'<button type = "button" id = "delet' + item.attendanceId + '" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.attendanceId + ')">Delete</button>' +
                    					'</td>' +

                    '</tr>';
			});
			$("#myTable>tbody").html(tableData);
}






function ajaxGet() {
    var keyword = $("#keyword").val();
     var studentId=$("#studentId").val();
	var tableData = "";
	$.ajax({
		type: "GET",
		url: "/attendance/student/"+studentId,
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
function delet(attendanceId){
	if (confirm('Do you really want to delete record?')) {
		var parent = $(this).parent().parent();
		alert(attendanceId);
		$.ajax({
		type: "DELETE",
			url: "/attendance/" +attendanceId,
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

	var attendanceId = parent.children("td:nth-child(1)");
	var date = parent.children("td:nth-child(2)");
	var attendance = parent.children("td:nth-child(3)");


    var buttons = parent.children("td:nth-child(4)");


	date.html("<input type='date' id='date' value='" + date.html() + "'/>");
	attendance.html("<input type='text' id='attendance' value='" + attendance.html() + "'/>");

	buttons.html("<button id='save' class= 'btn btn-success'>Save</button>");

});

$(document).delegate('#save', 'click', function() {
	var parent = $(this).parent().parent();

   var attendanceId = parent.children("td:nth-child(1)");
   	var date = parent.children("td:nth-child(2)");
   	var attendance = parent.children("td:nth-child(3)");



    var buttons = parent.children("td:nth-child(4)");



	$.ajax({
		type: "PUT",
		contentType: "application/json; charset=utf-8",
		url: "/attendance/"+attendanceId[0].innerText,
		data: JSON.stringify({
			'attendanceId': attendanceId[0].innerText, 'date': date.children("input[type=date]").val(),'attendance':attendance.children("input[type=text]").val(),


		}),
		cache: false,
		success: function() {
	    	date.html(date.children("input[type=date]").val());
	    	attendance.html(attendance.children("input[type=text]").val());


			buttons.html("<button class='btn btn-warning edit' id='" + attendanceId[0].innerText + "'>Edit</button>");
		},

	});



});



