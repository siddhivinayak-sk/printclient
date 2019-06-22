package com.dps.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author kumar-sand
 * Status:
 * 110 - New
 * 120 - Salt Added
 * 130 - Picked for Printing
 * 140 - Printing
 * 150 - Printing Successful
 * 160 - Exception
 * 170 - Ready to extinct
 * 200 - Cancel Initiated
 * 210 - Cancel in Progress
 * 210 - Cancelled
 * 300 - Exception
 */


@Entity
@Table(name = "PrintRequest")
public class PrintRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "PrinterName")
	private String printerName;
	
	@Column(name = "data")
	@Lob
	private String data;
	
	@Column(name = "salt")
	private String salt;
	
	@Column(name = "status")
	private Integer status;

	@Column(name = "messagecode")
	private String messageCode;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "createdTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name = "printTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date printTime;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<PrintJob> printJobs;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "chromaticity")
	private String chromaticity;
	
	@Column(name = "copies")
	private Integer copies;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "dialogTypeSelection")
	private String dialogTypeSelection;
	
	@Column(name = "fidelity")
	private String fidelity;
	
	@Column(name = "finishings")
	private String finishings;
	
	@Column(name = "jobHoldUntil")
	private String jobHoldUntil;
	
	@Column(name = "jobImpressions")
	private Integer jobImpressions;
	
	@Column(name = "jobKOctets")
	private Integer jobKOctets;
	
	@Column(name = "jobMediaSheets")
	private Integer jobMediaSheets;
	
	@Column(name = "jobName")
	private String jobName;
	
	@Column(name = "jobPriority")
	private Integer jobPriority;
	
	@Column(name = "jobSheets")
	private String jobSheets;
	
	@Column(name = "mediaName")
	private String mediaName;
	
	@Column(name = "mediaPrintableAreax")
	private Integer mediaPrintableAreax;
	
	@Column(name = "mediaPrintableAreay")
	private Integer mediaPrintableAreay;
	
	@Column(name = "mediaPrintableAreaw")
	private Integer mediaPrintableAreaw;
	
	@Column(name = "mediaPrintableAreah")
	private Integer mediaPrintableAreah;
	
	@Column(name = "mediaPrintableAreau")
	private Integer mediaPrintableAreau;
	
	@Column(name = "mediaSizeName")
	private String mediaSizeName;
	
	@Column(name = "mediaTray")
	private String mediaTray;
	
	@Column(name = "multipleDocumentHandling")
	private String multipleDocumentHandling;
	
	@Column(name = "numberUp")
	private Integer numberUp;
	
	@Column(name = "orientationRequested")
	private String orientationRequested;
	
	@Column(name = "pageRangesL")
	private Integer pageRangesL;
	
	@Column(name = "pageRangesU")
	private Integer pageRangesU;
	
	@Column(name = "presentationDirection")
	private String presentationDirection;
	
	@Column(name = "printerResolutionc")
	private Integer printerResolutionc;
	
	@Column(name = "printerResolutionf")
	private Integer printerResolutionf;

	@Column(name = "printerResolutionu")
	private Integer printerResolutionu;
	
	@Column(name = "printQuality")
	private String printQuality;
	
	@Column(name = "sheetCollate")
	private String sheetCollate;
	
	@Column(name = "sides")
	private String sides;

	@Column(name = "cancelId")
	private Long cancelId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getPrintTime() {
		return printTime;
	}

	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}

	public List<PrintJob> getPrintJobs() {
		return printJobs;
	}

	public void setPrintJobs(List<PrintJob> printJobs) {
		this.printJobs = printJobs;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getChromaticity() {
		return chromaticity;
	}

	public void setChromaticity(String chromaticity) {
		this.chromaticity = chromaticity;
	}

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDialogTypeSelection() {
		return dialogTypeSelection;
	}

	public void setDialogTypeSelection(String dialogTypeSelection) {
		this.dialogTypeSelection = dialogTypeSelection;
	}

	public String getFidelity() {
		return fidelity;
	}

	public void setFidelity(String fidelity) {
		this.fidelity = fidelity;
	}

	public String getFinishings() {
		return finishings;
	}

	public void setFinishings(String finishings) {
		this.finishings = finishings;
	}

	public String getJobHoldUntil() {
		return jobHoldUntil;
	}

	public void setJobHoldUntil(String jobHoldUntil) {
		this.jobHoldUntil = jobHoldUntil;
	}

	public Integer getJobImpressions() {
		return jobImpressions;
	}

	public void setJobImpressions(Integer jobImpressions) {
		this.jobImpressions = jobImpressions;
	}

	public Integer getJobKOctets() {
		return jobKOctets;
	}

	public void setJobKOctets(Integer jobKOctets) {
		this.jobKOctets = jobKOctets;
	}

	public Integer getJobMediaSheets() {
		return jobMediaSheets;
	}

	public void setJobMediaSheets(Integer jobMediaSheets) {
		this.jobMediaSheets = jobMediaSheets;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(Integer jobPriority) {
		this.jobPriority = jobPriority;
	}

	public String getJobSheets() {
		return jobSheets;
	}

	public void setJobSheets(String jobSheets) {
		this.jobSheets = jobSheets;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public Integer getMediaPrintableAreax() {
		return mediaPrintableAreax;
	}

	public void setMediaPrintableAreax(Integer mediaPrintableAreax) {
		this.mediaPrintableAreax = mediaPrintableAreax;
	}

	public Integer getMediaPrintableAreay() {
		return mediaPrintableAreay;
	}

	public void setMediaPrintableAreay(Integer mediaPrintableAreay) {
		this.mediaPrintableAreay = mediaPrintableAreay;
	}

	public Integer getMediaPrintableAreaw() {
		return mediaPrintableAreaw;
	}

	public void setMediaPrintableAreaw(Integer mediaPrintableAreaw) {
		this.mediaPrintableAreaw = mediaPrintableAreaw;
	}

	public Integer getMediaPrintableAreah() {
		return mediaPrintableAreah;
	}

	public void setMediaPrintableAreah(Integer mediaPrintableAreah) {
		this.mediaPrintableAreah = mediaPrintableAreah;
	}

	public Integer getMediaPrintableAreau() {
		return mediaPrintableAreau;
	}

	public void setMediaPrintableAreau(Integer mediaPrintableAreau) {
		this.mediaPrintableAreau = mediaPrintableAreau;
	}

	public String getMediaSizeName() {
		return mediaSizeName;
	}

	public void setMediaSizeName(String mediaSizeName) {
		this.mediaSizeName = mediaSizeName;
	}

	public String getMediaTray() {
		return mediaTray;
	}

	public void setMediaTray(String mediaTray) {
		this.mediaTray = mediaTray;
	}

	public String getMultipleDocumentHandling() {
		return multipleDocumentHandling;
	}

	public void setMultipleDocumentHandling(String multipleDocumentHandling) {
		this.multipleDocumentHandling = multipleDocumentHandling;
	}

	public Integer getNumberUp() {
		return numberUp;
	}

	public void setNumberUp(Integer numberUp) {
		this.numberUp = numberUp;
	}

	public String getOrientationRequested() {
		return orientationRequested;
	}

	public void setOrientationRequested(String orientationRequested) {
		this.orientationRequested = orientationRequested;
	}

	public Integer getPageRangesL() {
		return pageRangesL;
	}

	public void setPageRangesL(Integer pageRangesL) {
		this.pageRangesL = pageRangesL;
	}

	public Integer getPageRangesU() {
		return pageRangesU;
	}

	public void setPageRangesU(Integer pageRangesU) {
		this.pageRangesU = pageRangesU;
	}

	public String getPresentationDirection() {
		return presentationDirection;
	}

	public void setPresentationDirection(String presentationDirection) {
		this.presentationDirection = presentationDirection;
	}

	public Integer getPrinterResolutionc() {
		return printerResolutionc;
	}

	public void setPrinterResolutionc(Integer printerResolutionc) {
		this.printerResolutionc = printerResolutionc;
	}

	public Integer getPrinterResolutionf() {
		return printerResolutionf;
	}

	public void setPrinterResolutionf(Integer printerResolutionf) {
		this.printerResolutionf = printerResolutionf;
	}

	public Integer getPrinterResolutionu() {
		return printerResolutionu;
	}

	public void setPrinterResolutionu(Integer printerResolutionu) {
		this.printerResolutionu = printerResolutionu;
	}

	public String getPrintQuality() {
		return printQuality;
	}

	public void setPrintQuality(String printQuality) {
		this.printQuality = printQuality;
	}

	public String getSheetCollate() {
		return sheetCollate;
	}

	public void setSheetCollate(String sheetCollate) {
		this.sheetCollate = sheetCollate;
	}

	public String getSides() {
		return sides;
	}

	public void setSides(String sides) {
		this.sides = sides;
	}

	public Long getCancelId() {
		return cancelId;
	}

	public void setCancelId(Long cancelId) {
		this.cancelId = cancelId;
	}
	
	
	
}
