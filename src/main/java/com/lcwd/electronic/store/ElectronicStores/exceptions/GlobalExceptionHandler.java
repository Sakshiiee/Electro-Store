package com.lcwd.electronic.store.ElectronicStores.exceptions;

import com.lcwd.electronic.store.ElectronicStores.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handler for resource not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        // Call this method when resource not found exception occurs
        logger.info("Exception Handler Invoked !!");

        ApiResponseMessage response = ApiResponseMessage.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    //MethodArgumentNotFoundException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
      List<ObjectError>  allErrors =  ex.getBindingResult().getAllErrors();
      Map<String,Object> response = new HashMap<>();
      allErrors.stream().forEach(objectError -> {
         String message = objectError.getDefaultMessage();        //value
         String field = ((FieldError) objectError).getField();    //key
          response.put(field,message);
      });
      return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);


    }

    //handle bad api exception
    @ExceptionHandler(BadApiRequestException.class)
    public ResponseEntity<ApiResponseMessage> handleBadApiRequest(BadApiRequestException ex) {
        // Call this method when resource not found exception occurs
        logger.info("Bad Api Request!!");

        ApiResponseMessage response = ApiResponseMessage.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
