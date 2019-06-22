package com.dps.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dps.dtos.ThisSystem;
import com.dps.entities.PrintRequest;
import com.dps.entities.UserPrincipal;
import com.dps.handlers.CurrentUser;
import com.dps.services.PrinterService;

@RestController
@RequestMapping("/printer")
public class PrinterController {

	@Autowired
	private PrinterService printerService;
	
	//@CurrentUser UserPrincipal user
	
	@GetMapping("/list")
	public ResponseEntity<?> list() {
		return new ResponseEntity<ThisSystem>(printerService.getThisSystemDetails(), HttpStatus.OK);
	}
	
	@PostMapping("/addrequest")
	public ResponseEntity<?> addrequest(@RequestBody PrintRequest printRequest) {
		return new ResponseEntity<PrintRequest>(printerService.addRequest(printRequest), HttpStatus.OK);
	}

	@PostMapping("/addsalt")
	public ResponseEntity<?> addsalt(@RequestBody PrintRequest printRequest) {
		return new ResponseEntity<PrintRequest>(printerService.addSalt(printRequest), HttpStatus.OK);
	}
	
	@GetMapping("/listrequest")
	public ResponseEntity<?> listrequest() {
		return new ResponseEntity<List<PrintRequest>>(printerService.listRequest(), HttpStatus.OK);
	}
	
	@GetMapping("/listrequest/{id}")
	public ResponseEntity<?> listrequest(@PathParam("id") Long id) {
		return new ResponseEntity<PrintRequest>(printerService.listRequest(id).orElse(null), HttpStatus.OK);
	}
	
	@GetMapping("/testdoc")
	public ResponseEntity<?> generatetestdoc(@RequestParam(value = "name", required = false, defaultValue = "default.pdf")String name) throws Exception {
		return new ResponseEntity<PrintRequest>(printerService.generateDoc(name), HttpStatus.OK);
	}
}
