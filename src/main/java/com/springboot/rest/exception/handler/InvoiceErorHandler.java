package com.springboot.rest.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.rest.entity.ErrorType;
import com.springboot.rest.exception.InvoiceNotFoundException;

@RestControllerAdvice
public class InvoiceErorHandler {

	
	public ResponseEntity<ErrorType> hnadleNotFound(InvoiceNotFoundException ine){
		
		return new ResponseEntity<ErrorType>(new ErrorType(new Date(System.currentTimeMillis()).toString(),
				"404-Not Found",ine.getMessage()),HttpStatus.NOT_FOUND);
	}
}
