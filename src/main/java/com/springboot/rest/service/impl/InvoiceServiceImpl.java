package com.springboot.rest.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest.entity.Invoice;
import com.springboot.rest.exception.InvoiceNotFoundException;
import com.springboot.rest.repo.Invoicerepository;
import com.springboot.rest.service.InvoiceService;
import com.springboot.rest.util.InvoiceUtil;
@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	
	@Autowired(required=true)
	private Invoicerepository invoiceRepository; 
	
	@Autowired(required=true)
	private InvoiceUtil invoiceUtil;

	@Override
	public Long saveInvoice(Invoice inv) {
		invoiceUtil.CaluclateFinalAmountIncludingGST(inv);
		Long id=invoiceRepository.save(inv).getId();
		return id;
	}

	@Override
	public void updateInvoice(Invoice e) {
		invoiceUtil.CaluclateFinalAmountIncludingGST(e);
		invoiceRepository.save(e);
	}
	
	@Override
	public void deleteInvoice(Long id) {
     Invoice inv=getOneInvoice(id);
     invoiceRepository.delete(inv);
	}
	
	public Optional<Invoice> getSingleInvoice(Long id)
	{
		return invoiceRepository.findById(id);
	}

	@Override
	public Invoice getOneInvoice(Long id) {
		Invoice inv=invoiceRepository.findById(id)
				   .orElseThrow(()->new InvoiceNotFoundException(
							new StringBuffer().append("Product  '")
							.append(id)
							.append("' not exist")
							.toString())
							);
				   return inv;
	}

	@Override
	public List<Invoice> getAllInvoices() {
		List<Invoice> list=invoiceRepository.findAll();
		list.sort((ob1,ob2)->ob1.getId().intValue()-ob2.getId().intValue());
		
		return list;
	}

	@Override
	public boolean isInvoiceExists(Long id) {
		return invoiceRepository.existsById(id);
	}

	@Override
	@Transactional
	public Integer updateInvoiceNumberById(String number, Long id) {
		
		if(!invoiceRepository.existsById(id))
		{
			throw new InvoiceNotFoundException(new StringBuffer().append("Invoice '").append(id).append("' not exist").toString());
		}
		return invoiceRepository.updateInvoiceNumberById(number, id);
	}
	

}
