package com.springboot.rest.util;

import org.springframework.stereotype.Component;

import com.springboot.rest.entity.Invoice;

@Component
public class InvoiceUtil {

	public Invoice CaluclateFinalAmountIncludingGST(Invoice inv) {
		var amount=inv.getAmount();
		var gst=0.1;
		var finalAmount=amount+(amount*gst);
		inv.setFinalAmount(finalAmount);
		return inv;
	}
	public void copyNonNullValues(Invoice req, Invoice db)
	{
		if(req.getName()!=null)
		{
			db.setName(req.getName());
	
		}
		if(req.getAmount()!=null)
		{
			db.setAmount(req.getAmount());
		}
		if(req.getNumber()!=null)
		{
			db.setNumber(req.getNumber());
		}
		if(req.getRecivedDate()!=null)
		{
			db.setRecivedDate(req.getRecivedDate());
		}
		if(req.getType()!=null)
		{
			db.setType(req.getType());
		}
		if(req.getVendor()!=null)
		{
			db.setVendor(req.getVendor());
		}
		if(req.getComments()!=null)
		{
			db.setComments(req.getComments());
		}
		}
	
	}

