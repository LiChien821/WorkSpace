package com.howhow.course.exception;

import java.util.Date;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(WrongInputException.class)
	public ResponseEntity<?> wrongInputIDException(WrongInputException exception,WebRequest request){
		
		
		ExceptionDetail excDetail=new ExceptionDetail(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(excDetail,HttpStatus.NOT_FOUND);
	}
}
