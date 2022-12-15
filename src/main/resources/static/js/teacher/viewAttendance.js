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
