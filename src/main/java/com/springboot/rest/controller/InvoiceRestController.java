package com.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.entity.Invoice;
import com.springboot.rest.exception.InvoiceNotFoundException;
import com.springboot.rest.service.InvoiceService;
import com.springboot.rest.util.InvoiceUtil;

@RestController
@RequestMapping("/invoice/rest")	
public class InvoiceRestController {

	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private InvoiceUtil invoiceUtil;
	
	public ResponseEntity<String> saveInvoice(@RequestBody Invoice inv)
	{
		ResponseEntity<String> responseEntity=null;
		try
		{
			Long id=invoiceService.saveInvoice(inv);
			responseEntity=new ResponseEntity<>("Invoice '"+id+"' created",HttpStatus.CREATED);
			}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity=new ResponseEntity<>("Unable to save Invoice",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
		
	}
	@GetMapping("/getAllInvoices")
	public ResponseEntity<?> getAllInvoices()
	{
		ResponseEntity< ?> responseEntity=null;
		try
		{
			List<Invoice> invoiceList=invoiceService.getAllInvoices();
			responseEntity=new ResponseEntity<List<Invoice>> (invoiceList,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity=new ResponseEntity<String>("Unable to find Invoice",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getOneInvoice(@PathVariable Long id)
	{
		ResponseEntity<?> responseEntity=null;
		
		try
		{
			Invoice invoice=invoiceService.getOneInvoice(id);
			responseEntity=new ResponseEntity<Invoice>(invoice,HttpStatus.OK);
		}
		catch(InvoiceNotFoundException ine)
		{
			throw ine;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity=new ResponseEntity<String>("Unable to find Invoice",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
		
	}
}

