$(document).ready(
	function() {

		// SUBMIT FORM
		$("#viewClass").submit(function(event) {
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
                    '<td id = "ID' + item.classId + '">' + item.classId + '</td>' +
					'<td id = "Name' + item.className + '">' + item.className + '</td>' +

					'<td id = "Class Room' + item.classRoom + '">' + item.classRoom + '</td>' +

					'<td>' +
					'<button type = "button" id = "edit' + item.classId + '" class = "btn btn-warning btn-md edit">Edit</button>' +
					'</td>' +
					'<td>' +
					'<button type = "button" id = "delet' + item.classId + '" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.classId + ')">Delete</button>' +
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
		url: "/class/",
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
function delet(classId){
	if (confirm('Do you really want to delete record?')) {
		var parent = $(this).parent().parent();
		alert(classId);
		$.ajax({
		type: "DELETE",
			url: "/class/" +classId,
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

	var classId = parent.children("td:nth-child(1)");
	var className = parent.children("td:nth-child(2)");
	var classRoom = parent.children("td:nth-child(3)");


    var buttons = parent.children("td:nth-child(4)");


	className.html("<input type='text' id='className' value='" + className.html() + "'/>");
	classRoom.html("<input type='text' id='classRoom' value='" + classRoom.html() + "'/>");

	buttons.html("<button id='save' class= 'btn btn-success'>Save</button>");

});

$(document).delegate('#save', 'click', function() {
	var parent = $(this).parent().parent();

    var classId = parent.children("td:nth-child(1)");

	var className = parent.children("td:nth-child(2)");
	var classRoom = parent.children("td:nth-child(3)");



    var buttons = parent.children("td:nth-child(4)");



	$.ajax({
		type: "PUT",
		contentType: "application/json; charset=utf-8",
		url: "/class/"+classId[0].innerText,
		data: JSON.stringify({
			'studentId': classId[0].innerText, 'className': className.children("input[type=text]").val(),'classRoom':classRoom.children("input[type=text]").val(),


		}),
		cache: false,
		success: function() {
	    	className.html(className.children("input[type=text]").val());
	    	classRoom.html(classRoom.children("input[type=text]").val());


			buttons.html("<button class='btn btn-warning edit' id='" + classId[0].innerText + "'>Edit</button>");
		},

	});



});


