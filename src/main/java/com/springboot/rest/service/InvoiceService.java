package com.springboot.rest.service;

import java.util.List;

import com.springboot.rest.entity.Invoice;



public interface InvoiceService {

	
	Long saveInvoice(Invoice inv);
	void updateInvoice(Invoice e);
	void deleteInvoice(Long id);
	Invoice getOneInvoice(Long id);
	List<Invoice> getAllInvoices();
	boolean isInvoiceExists(Long id);
	Integer updateInvoiceByNumber(String number,Long id);
	
	
}
