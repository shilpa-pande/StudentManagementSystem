$(document).ready(
	function() {

		// SUBMIT FORM
		$("#teacherViewClass").submit(function(event) {
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
                   '<td id = "Students' + item.classId + '">' + '<a href="/teacherView/viewStudent/'+ item.classId + '"+" class = "btn btn-primary btn-md "">' + 'Students' +
                                                       					'</td>' +


					'</tr>';
			});
			$("#myTable>tbody").html(tableData);
}






function ajaxGet() {
    var keyword = $("#keyword").val();
    var teacherId=$("#teacherId").val();
	var tableData = "";
	$.ajax({
		type: "GET",
		url: "/class/teacher/"+teacherId,
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
