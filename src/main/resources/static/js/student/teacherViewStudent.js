$(document).ready(
	function() {

		// SUBMIT FORM
		$("#teacherViewStudent").submit(function(event) {
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
					                                        					'</td>' +
                    					'</tr>';
			});
			$("#myTable>tbody").html(tableData);
}






function ajaxGet() {
    var keyword = $("#keyword").val();
     var classId=$("#classId").val();
	var tableData = "";
	$.ajax({
		type: "GET",
		url: "/students/class/"+classId,
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
