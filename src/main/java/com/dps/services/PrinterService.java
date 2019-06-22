package com.dps.services;

import java.util.List;
import java.util.Optional;

import com.dps.dtos.ThisSystem;
import com.dps.entities.PrintRequest;


public interface PrinterService {

	public void init();
	
	public ThisSystem getThisSystemDetails();
	
	public void updatePrintJobsStatus();
	
	public PrintRequest addRequest(PrintRequest pr);
	
	public PrintRequest addSalt(PrintRequest pr);
	
	public List<PrintRequest> listRequest();
	
	public Optional<PrintRequest> listRequest(Long id);
	
	public PrintRequest generateDoc(String name) throws Exception;
}
