package com.studentmanagement.exceptions;

import com.studentmanagement.Dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        String message=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String , String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
//        Map<String,String> resp=new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error)->{
//           String fieldName= ((FieldError)error).getField();
//           String message=error.getDefaultMessage();
//           resp.put(fieldName,message);
//
//        });
//
//        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> ApiExceptionHandler(ApiException ex){

        String message=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,true);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
