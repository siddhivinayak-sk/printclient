package com.dps.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Printers")
public class Printer extends UserDateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	
	@Column(name = "colorSupported")
	private String colorSupported;
	
	@Column(name = "queuedJobCount")
	private Integer queuedJobCount;
	
	@Column(name = "printerIsAcceptingJobs")
	private String printerIsAcceptingJobs;
	
	@Column(name = "printerName")
	private String printerName;
	
	@Column(name = "pagesPerMinute")
	private Integer pagesPerMinute;
	
	@Column(name = "pagesPerMinuteColor")
	private Integer pagesPerMinuteColor;
	
	@Column(name = "")
	private String printerInfo;
	
	@Column(name = "printerLocation")
	private String printerLocation;
	
	@Column(name = "printerMakeAndModel")
	private String printerMakeAndModel;
	
	@Column(name = "printerMoreInfo")
	private String printerMoreInfo;
	
	@Column(name = "printerMoreInfoManufacturer")
	private String printerMoreInfoManufacturer;
	
	@Column(name = "printerState")
	private String printerState;
	
	@Column(name = "printerURI")
	private String printerURI;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Setting> settings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColorSupported() {
		return colorSupported;
	}

	public void setColorSupported(String colorSupported) {
		this.colorSupported = colorSupported;
	}

	public Integer getQueuedJobCount() {
		return queuedJobCount;
	}

	public void setQueuedJobCount(Integer queuedJobCount) {
		this.queuedJobCount = queuedJobCount;
	}

	public String getPrinterIsAcceptingJobs() {
		return printerIsAcceptingJobs;
	}

	public void setPrinterIsAcceptingJobs(String printerIsAcceptingJobs) {
		this.printerIsAcceptingJobs = printerIsAcceptingJobs;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public Integer getPagesPerMinute() {
		return pagesPerMinute;
	}

	public void setPagesPerMinute(Integer pagesPerMinute) {
		this.pagesPerMinute = pagesPerMinute;
	}

	public Integer getPagesPerMinuteColor() {
		return pagesPerMinuteColor;
	}

	public void setPagesPerMinuteColor(Integer pagesPerMinuteColor) {
		this.pagesPerMinuteColor = pagesPerMinuteColor;
	}

	public String getPrinterInfo() {
		return printerInfo;
	}

	public void setPrinterInfo(String printerInfo) {
		this.printerInfo = printerInfo;
	}

	public String getPrinterLocation() {
		return printerLocation;
	}

	public void setPrinterLocation(String printerLocation) {
		this.printerLocation = printerLocation;
	}

	public String getPrinterMakeAndModel() {
		return printerMakeAndModel;
	}

	public void setPrinterMakeAndModel(String printerMakeAndModel) {
		this.printerMakeAndModel = printerMakeAndModel;
	}

	public String getPrinterMoreInfo() {
		return printerMoreInfo;
	}

	public void setPrinterMoreInfo(String printerMoreInfo) {
		this.printerMoreInfo = printerMoreInfo;
	}

	public String getPrinterMoreInfoManufacturer() {
		return printerMoreInfoManufacturer;
	}

	public void setPrinterMoreInfoManufacturer(String printerMoreInfoManufacturer) {
		this.printerMoreInfoManufacturer = printerMoreInfoManufacturer;
	}

	public String getPrinterState() {
		return printerState;
	}

	public void setPrinterState(String printerState) {
		this.printerState = printerState;
	}

	public String getPrinterURI() {
		return printerURI;
	}

	public void setPrinterURI(String printerURI) {
		this.printerURI = printerURI;
	}

	public List<Setting> getSettings() {
		return settings;
	}

	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}



	
	
	
}
