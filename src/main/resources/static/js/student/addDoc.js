 $(document).ready(function () {
            var student = {};
            var dynamicURL = "";
            var methodName = "";

            $('#addDoc').click(function () {
                student.doc = $('#doc').val();

                var studentId = $('#studentId').val();
                if(studentId){
                    //update it
                    dynamicURL = "/students/uploadDoc"+studentId;
                    methodName = "POST";
                }
                var studentObj = JSON.stringify(student);
                $.ajax({
                    url: dynamicURL,
                    method: methodName,
                    data: studentObj,
                  contentType : "application/json",
                    success: function () {
                        alert('Saved successfully');


                    },
                    error: function (error) {
                        alert(error);
                    }
                })
            })
        })

