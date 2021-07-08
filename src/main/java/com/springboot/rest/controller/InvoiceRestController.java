package com.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping("/saveInvoice")
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
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteInvoice(@PathVariable Long id)
	{
		ResponseEntity<String> responseEntity=null;
		try
		{
			invoiceService.deleteInvoice(id);
			responseEntity=new ResponseEntity<String>("Invoice '"+id+"'deleted",HttpStatus.OK);
		}
		catch(InvoiceNotFoundException ine)
		{
			throw ine;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity=new ResponseEntity<String>("Unable to delete invoice",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
		
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateInvoice(@PathVariable long id,@RequestBody Invoice invoice)
	{
		ResponseEntity<String> responseEntity=null;
		try
		{
			Invoice inv=invoiceService.getOneInvoice(id);
			invoiceUtil.copyNonNullValues(invoice, inv);
			invoiceService.updateInvoice(inv);
			responseEntity=new ResponseEntity<String>("Invoice'"+id+"'+Updated",HttpStatus.RESET_CONTENT);
			}
		catch(InvoiceNotFoundException ine)
		{
			throw ine;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity=new ResponseEntity<String>("Unable to update invoice",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	@PatchMapping("/modify/{id}/{number}")
	public ResponseEntity<String> updateInvoiceNumberById(@PathVariable Long id,@PathVariable String number)
	{
		ResponseEntity<String> responseEntity=null;
		try
		{
			invoiceService.updateInvoiceNumberById(number, id);
			responseEntity=new ResponseEntity<String>("Invoice'"+number+"'+Updated",HttpStatus.PARTIAL_CONTENT);
		}
		catch(InvoiceNotFoundException ine)
		{
			throw ine;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity=new ResponseEntity<String>("Unable to update invoice",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return responseEntity;
	}
}

