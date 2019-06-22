package com.dps.schedulars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dps.services.PrinterService;

@Component
public class PrinterSchedular {

	@Autowired
	private PrinterService printerService;
	
	
	@Scheduled(fixedDelay = 30000)
	public void refreshPrinters() {
		printerService.init();
	}
	
	@Scheduled(fixedDelay = 10000)
	public void printJobStatusUpdate() {
		printerService.updatePrintJobsStatus();
	}

}
