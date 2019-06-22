package com.dps.services;

import java.util.Arrays;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.springframework.stereotype.Service;

import com.dps.entities.PrintRequest;
import com.dps.repositories.PrintRequestRepository;

@Service
public class PrintJobServiceImpl implements PrintJobService {

	private PrintRequestRepository printRequestRepository;
	
	@Override
	public void process120(PrintRequest request) {
		request.setStatus(130);
		printRequestRepository.save(request);
		try {
	        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
	        PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
	        PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
	        if (null == ps || ps.length == 0) {
	            throw new IllegalStateException("No Printer found");
	        }
	        

	        request.setStatus(140);
			printRequestRepository.save(request);
		}
		catch(Exception ex) {
			request.setStatus(160);
			printRequestRepository.save(request);
		}
	}

}
