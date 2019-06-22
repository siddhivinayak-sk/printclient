package com.dps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.print.attribute.standard.MediaPrintableArea;

@Entity
@Table(name = "PrintJobs")
public class PrintJob extends UserDateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	
/*	
	private String chromaticity;
	private String compression;
	private Integer copies;
	private Date _dateTimeAtCompleted;
	private Date _dateTimeAtCreation;
	private Date _dateTimeAtProcessing;
	private String _jobOriginatingUserName;
	private String _jobStateReason;
	private String[] jobStateReasons;
	private String destination;
	private String dialogTypeSelection;
	private String documentName;
	private String fidelity;
	private String finishings;
	private Date jobHoldUntil;
	private Integer jobImpressions;
	private Integer jobImpressionsCompleted;
	private Integer jobKOctets;
	private Integer jobKOctetsProcessed;
	private Integer jobMediaSheets;
	private Integer jobMediaSheetsProcessed;
	private String jobMessageFromOperator;
	private String jobName;
	private Integer jobPriority;
	private String jobSheets;
	private String mediaName;
	private MediaPrintableArea mediaPrintableArea;
	private Integer[] mediaSize;
	private String mediaSizeName;
	private String mediaTray;
	private Integer numberOfDocuments;
	private Integer numberUp;
	private String presentationDirection;
	private String orientationRequested;
	private Integer[] pageRanges;
	private Integer[] printerResolution;
	private String printQuality;
	private String requestingUserName;
	private String severity;
	private String sheetCollate;
	private String sides;
*/	
	
	
	
	
	
	
	
	
	
	
	
}
