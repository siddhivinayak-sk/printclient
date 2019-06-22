package com.dps.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.dps.services.PrinterService;

@Component
public class CustomEventListener implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private PrinterService printerService; 
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		//printerService.init();
	}

}
