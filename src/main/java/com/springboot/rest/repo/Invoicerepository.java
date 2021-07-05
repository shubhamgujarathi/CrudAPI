package com.springboot.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.entity.Invoice;

public interface Invoicerepository extends JpaRepository<Invoice, Long> {
	
	Integer updateInvoiceNumberById(String number,Long id);
}
