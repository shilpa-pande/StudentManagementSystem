package com.studentmanagement.restController;

import com.studentmanagement.Dto.ApiResponse;
import com.studentmanagement.Dto.DocResponse;
import com.studentmanagement.Dto.StudentDto;
import com.studentmanagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    //register new user api
    @PostMapping("/register")
    public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentDto studentDto) {

        StudentDto registerNewStudent = this.studentService.registerNewStudent(studentDto);

        return new ResponseEntity<>(registerNewStudent, HttpStatus.CREATED);
    }

    //api for creating students
    @PostMapping("/class/{classId}")
    public ResponseEntity<StudentDto> createUser(@Valid @RequestBody StudentDto studentDto, @PathVariable Integer classId) {
        StudentDto createStudentDto = this.studentService.createStudent(studentDto, classId);
        return new ResponseEntity<>(createStudentDto, HttpStatus.CREATED);
    }


    //update student by id
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDto> updatePost(@RequestBody StudentDto studentDto, @PathVariable Integer studentId) {
        StudentDto updateStudent = this.studentService.updateStudent(studentDto, studentId);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    //get students details by id
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Integer studentId) {
        StudentDto studentDto = this.studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }


    //get All Students
    @GetMapping("/")

    public ResponseEntity<List<StudentDto>> getAllStudents(@RequestParam String keyword) {
        if (keyword == null) {
            return ResponseEntity.ok(this.studentService.getAllStudents());
        } else {
            return ResponseEntity.ok(this.studentService.getStudentByKeyword(keyword));
        }
    }


    // delete post by id
    @DeleteMapping("/{studentId}")
    public ApiResponse deleteStudent(@PathVariable Integer studentId) {
        this.studentService.deleteStudent(studentId);
        return new ApiResponse("student is successfully deleted", true);
    }




    //upload student document
    @PostMapping("/uploadDoc/{studentId}")
    public ResponseEntity<StudentDto> uploadStudentDoc(@RequestParam("doc") MultipartFile files, @PathVariable Integer studentId) throws IOException {
        StudentDto studentDto = this.studentService.uploadStudentDoc(files, studentId);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);

    }


    //get all documents
    @GetMapping("/docs")
    public ResponseEntity<List<DocResponse>> getListFiles() {
        List<DocResponse> files = studentService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/students/doc/")
                    .path(String.valueOf(dbFile.getStudentId()))
                    .toUriString();

            return new DocResponse(
                    dbFile.getDocName(),
                    fileDownloadUri,
                    dbFile.getType()
                   );
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }




    //get doc by id
    @GetMapping("/doc/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        StudentDto fileDB = studentService.getDoc(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getDocName() + "\"")
                .body(fileDB.getData());
    }

}


