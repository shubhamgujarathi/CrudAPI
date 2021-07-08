package com.springboot.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.rest.entity.Invoice;

public interface Invoicerepository extends JpaRepository<Invoice, Long> {
	
	@Modifying
	@Query("Update Invoice SET number=:number where id=:id")
	Integer updateInvoiceNumberById(String number,Long id);
}
