package com.dps.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.Fidelity;
import javax.print.attribute.standard.Finishings;
import javax.print.attribute.standard.JobSheets;
import javax.print.attribute.standard.MediaName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import javax.print.attribute.standard.MultipleDocumentHandling;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PagesPerMinute;
import javax.print.attribute.standard.PagesPerMinuteColor;
import javax.print.attribute.standard.PresentationDirection;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterInfo;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterLocation;
import javax.print.attribute.standard.PrinterMakeAndModel;
import javax.print.attribute.standard.PrinterMoreInfo;
import javax.print.attribute.standard.PrinterMoreInfoManufacturer;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterURI;
import javax.print.attribute.standard.QueuedJobCount;
import javax.print.attribute.standard.SheetCollate;
import javax.print.attribute.standard.Sides;
import javax.transaction.Transactional;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dps.dtos.ThisSystem;
import com.dps.entities.PrintRequest;
import com.dps.entities.Printer;
import com.dps.entities.Setting;
import com.dps.repositories.PrintRequestRepository;
import com.dps.repositories.PrinterRepository;
import com.dps.repositories.SettingRepository;
import com.dps.utils.CryptographyUtils;

@Service
public class PrinterServiceImpl implements PrinterService {

	@Autowired
	private PrinterRepository printerRepository;

	@Autowired
	private SettingRepository settingsRepository;

	@Autowired
	private PrintRequestRepository printRequestRepository;

	@Autowired
	private JobLauncher jobLauncher;
	
	@Qualifier("job1")
	@Autowired
	private Job job;
	
	
	@Override
	@Transactional
	public void init() {
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
        PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
        //DocFlavor flavor = null;
        //PrintRequestAttributeSet patts = null;
        
        PrintService[] psArray = PrintServiceLookup.lookupPrintServices(flavor, patts);
        
        if(null != psArray && psArray.length > 0) {
        	Stream.of(psArray).forEach((ps) -> {
        		
        		List<Printer> printerList = printerRepository.findByName(ps.getName());
        		Printer printer = null;
        		if(null != printerList && !printerList.isEmpty()) {
        			printer = printerList.get(0);
        		}
        		else {
        			printer = new Printer();
            		printer.setCreatedAt(new Date());
            		printer.setCreatedBy(Long.valueOf("0"));
        		}
        		
        		ColorSupported colorSupported = ps.getAttribute(ColorSupported.class);
        		printer.setColorSupported((null != colorSupported)?colorSupported.toString():"");
        		
        		QueuedJobCount queuedJobCount = ps.getAttribute(QueuedJobCount.class);
        		printer.setQueuedJobCount((null != queuedJobCount)?queuedJobCount.getValue():0);
        		
        		PrinterIsAcceptingJobs printerIsAcceptingJobs = ps.getAttribute(PrinterIsAcceptingJobs.class);
        		printer.setPrinterIsAcceptingJobs(null != printerIsAcceptingJobs?printerIsAcceptingJobs.toString():"");
        		
        		PrinterName printerName = ps.getAttribute(PrinterName.class);
        		printer.setPrinterName(null != printerName?printerName.toString():"");
        		
        		PagesPerMinute pagesPerMinute = ps.getAttribute(PagesPerMinute.class);
        		printer.setPagesPerMinute(null != pagesPerMinute?pagesPerMinute.getValue():0);
        		
        		PagesPerMinuteColor pagesPerMinuteColor = ps.getAttribute(PagesPerMinuteColor.class);
        		printer.setPagesPerMinuteColor(null != pagesPerMinuteColor?pagesPerMinuteColor.getValue():0);
        		
        		PrinterInfo printerInfo = ps.getAttribute(PrinterInfo.class);
        		printer.setPrinterInfo(null != printerInfo?pagesPerMinuteColor.toString():"");
        		
        		PrinterLocation printerLocation = ps.getAttribute(PrinterLocation.class);
        		printer.setPrinterLocation(null != printerLocation?printerLocation.toString():"");
        		
        		PrinterMakeAndModel printerMakeAndModel = ps.getAttribute(PrinterMakeAndModel.class);
        		printer.setPrinterMakeAndModel(null != printerMakeAndModel?printerMakeAndModel.toString():"");
        		
        		PrinterMoreInfo printerMoreInfo = ps.getAttribute(PrinterMoreInfo.class);
        		printer.setPrinterMoreInfo(null != printerMoreInfo?printerMoreInfo.toString():"");
        		
        		PrinterMoreInfoManufacturer printerMoreInfoManufacturer = ps.getAttribute(PrinterMoreInfoManufacturer.class);
        		printer.setPrinterMoreInfoManufacturer(null != printerMoreInfoManufacturer?printerMoreInfoManufacturer.toString():"");
        		
        		PrinterState printerState = ps.getAttribute(PrinterState.class);
        		printer.setPrinterState(null != printerState?printerState.toString():"");
        		
        		PrinterURI printerURI = ps.getAttribute(PrinterURI.class);
        		printer.setPrinterURI(null != printerURI?printerURI.toString():"");
        		
        		//Object copiesSupported = ps.getSupportedAttributeValues(CopiesSupported.class, flavor, patts);
        		//Object jobImpressionsSupported = ps.getSupportedAttributeValues(JobImpressionsSupported.class, flavor, patts);
        		//Object  jobKOctetsSupported = ps.getSupportedAttributeValues(JobKOctetsSupported.class, flavor, patts);
        		//Object  jobMediaSheetsSupported = ps.getSupportedAttributeValues(JobMediaSheetsSupported.class, flavor, patts);
        		//Object  jobPrioritySupported = ps.getSupportedAttributeValues(JobPrioritySupported.class, flavor, patts);
        		//Object  numberUpSupported = ps.getSupportedAttributeValues(NumberUpSupported.class, flavor, patts);
        		//Object  pdlOverrideSupported = ps.getSupportedAttributeValues(PDLOverrideSupported.class, flavor, patts);
        		//Object  printerStateReason = ps.getSupportedAttributeValues(PrinterStateReason.class, flavor, patts);
        		//Object  referenceUriSchemesSupported = ps.getSupportedAttributeValues(ReferenceUriSchemesSupported.class, flavor, patts);

        		List<Long> tempSettings = null;
        		if(null != printer.getSettings() && !printer.getSettings().isEmpty()) {
            		tempSettings = printer.getSettings().stream().map((e) -> e.getId()).collect(Collectors.toList());
        		}
        		
        		printer.setSettings(null);
        		printerRepository.saveAndFlush(printer);
        		
        		if(null != tempSettings && !tempSettings.isEmpty()) {
            		tempSettings.stream().forEach((e) -> settingsRepository.deleteById(e));
        			settingsRepository.flush();
        		}
        		
        		List<Setting> settings = new ArrayList<Setting>();
        		for(Class clazz:ps.getSupportedAttributeCategories()) {
        			Setting tmp = new Setting();
        			tmp.setKey(clazz.getSimpleName());
        			Object ob = ps.getDefaultAttributeValue(clazz);
        			tmp.setValue((null != ob)?ob.toString():"");
        			tmp.setCreatedAt(new Date());
        			tmp.setUpdatedAt(new Date());
        			tmp.setCreatedBy(0l);
        			tmp.setUpdatedBy(0l);
        			settings.add(tmp);
        		}
        		settingsRepository.saveAll(settings);
        		printer.setSettings(settings);
        		
        		printer.setName(ps.getName());
        		printer.setUpdatedAt(new Date());
        		printer.setUpdatedBy(Long.valueOf("0"));
        		printerRepository.saveAndFlush(printer);
        	});
        }
		
	}
	
	@Override
	public ThisSystem getThisSystemDetails() {
		ThisSystem system = new ThisSystem();
		List<Printer> printers = printerRepository.findAll();
		system.setPrinters(printers);
		try {
			system.setHostname(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			system.setHostname("localhost");
		}
		
		String[] chromaticity = new String[2];
		chromaticity[0] = Chromaticity.COLOR.toString();
		chromaticity[1] = Chromaticity.MONOCHROME.toString();
		system.setChromaticity(chromaticity);
		
		String[] dialogTypeSelection = new String[2];
		dialogTypeSelection[0] = DialogTypeSelection.COMMON.toString();
		dialogTypeSelection[1] = DialogTypeSelection.NATIVE.toString();
		system.setDialogTypeSelection(dialogTypeSelection);
		
		String[] fidelity = new String[2];
		fidelity[0] = Fidelity.FIDELITY_TRUE.toString();
		fidelity[1] = Fidelity.FIDELITY_FALSE.toString();
		system.setFidelity(fidelity);
		
		String[] finishings = new String[18];
		finishings[0] = Finishings.BIND.toString();
		finishings[1] = Finishings.COVER.toString();
		finishings[2] = Finishings.EDGE_STITCH.toString();
		finishings[3] = Finishings.EDGE_STITCH_BOTTOM.toString();
		finishings[4] = Finishings.EDGE_STITCH_LEFT.toString();
		finishings[5] = Finishings.EDGE_STITCH_RIGHT.toString();
		finishings[6] = Finishings.EDGE_STITCH_TOP.toString();
		finishings[7] = Finishings.NONE.toString();
		finishings[8] = Finishings.SADDLE_STITCH.toString();
		finishings[9] = Finishings.STAPLE.toString();
		finishings[10] = Finishings.STAPLE_BOTTOM_LEFT.toString();
		finishings[11] = Finishings.STAPLE_BOTTOM_RIGHT.toString();
		finishings[12] = Finishings.STAPLE_DUAL_BOTTOM.toString();
		finishings[13] = Finishings.STAPLE_DUAL_LEFT.toString();
		finishings[14] = Finishings.STAPLE_DUAL_RIGHT.toString();
		finishings[15] = Finishings.STAPLE_DUAL_TOP.toString();
		finishings[16] = Finishings.STAPLE_TOP_LEFT.toString();
		finishings[17] = Finishings.STAPLE_TOP_RIGHT.toString();
		system.setFinishings(finishings);
		
		String[] jobSheets = new String[2];
		jobSheets[0] = JobSheets.NONE.toString();
		jobSheets[1] = JobSheets.STANDARD.toString();
		system.setJobSheets(jobSheets);
		
		String[] mediaName = new String[4];
		mediaName[0] = MediaName.ISO_A4_TRANSPARENT.toString();
		mediaName[1] = MediaName.ISO_A4_WHITE.toString();
		mediaName[2] = MediaName.NA_LETTER_TRANSPARENT.toString();
		mediaName[3] = MediaName.NA_LETTER_WHITE.toString();
		system.setMediaName(mediaName);
		
		Integer[] mediaPrintableAreaUnits = new Integer[2];
		mediaPrintableAreaUnits[0] = MediaPrintableArea.INCH;
		mediaPrintableAreaUnits[1] = MediaPrintableArea.MM;
		system.setMediaPrintableAreaUnits(mediaPrintableAreaUnits);
		
		String[] mediaSizeName = new String[73];
		mediaSizeName[0] = MediaSizeName.A.toString();
		mediaSizeName[1] = MediaSizeName.B.toString();
		mediaSizeName[2] = MediaSizeName.C.toString();
		mediaSizeName[3] = MediaSizeName.D.toString();
		mediaSizeName[4] = MediaSizeName.E.toString();
		mediaSizeName[5] = MediaSizeName.EXECUTIVE.toString();
		mediaSizeName[6] = MediaSizeName.FOLIO.toString();
		mediaSizeName[7] = MediaSizeName.INVOICE.toString();
		mediaSizeName[8] = MediaSizeName.ISO_A0.toString();
		mediaSizeName[9] = MediaSizeName.ISO_A1.toString();
		mediaSizeName[10] = MediaSizeName.ISO_A10.toString();
		mediaSizeName[11] = MediaSizeName.ISO_A2.toString();
		mediaSizeName[12] = MediaSizeName.ISO_A3.toString();
		mediaSizeName[13] = MediaSizeName.ISO_A4.toString();
		mediaSizeName[14] = MediaSizeName.ISO_A5.toString();
		mediaSizeName[15] = MediaSizeName.ISO_A6.toString();
		mediaSizeName[16] = MediaSizeName.ISO_A7.toString();
		mediaSizeName[17] = MediaSizeName.ISO_A8.toString();
		mediaSizeName[18] = MediaSizeName.ISO_A9.toString();
		mediaSizeName[19] = MediaSizeName.ISO_B0.toString();
		mediaSizeName[20] = MediaSizeName.ISO_B1.toString();
		mediaSizeName[21] = MediaSizeName.ISO_B10.toString();
		mediaSizeName[22] = MediaSizeName.ISO_B2.toString();
		mediaSizeName[23] = MediaSizeName.ISO_B3.toString();
		mediaSizeName[24] = MediaSizeName.ISO_B4.toString();
		mediaSizeName[25] = MediaSizeName.ISO_B5.toString();
		mediaSizeName[26] = MediaSizeName.ISO_B6.toString();
		mediaSizeName[27] = MediaSizeName.ISO_B7.toString();
		mediaSizeName[28] = MediaSizeName.ISO_B8.toString();
		mediaSizeName[29] = MediaSizeName.ISO_B9.toString();
		mediaSizeName[30] = MediaSizeName.ISO_C0.toString();
		mediaSizeName[31] = MediaSizeName.ISO_C1.toString();
		mediaSizeName[32] = MediaSizeName.ISO_C2.toString();
		mediaSizeName[33] = MediaSizeName.ISO_C3.toString();
		mediaSizeName[34] = MediaSizeName.ISO_C4.toString();
		mediaSizeName[35] = MediaSizeName.ISO_C5.toString();
		mediaSizeName[36] = MediaSizeName.ISO_C6.toString();
		mediaSizeName[37] = MediaSizeName.ISO_DESIGNATED_LONG.toString();
		mediaSizeName[38] = MediaSizeName.ITALY_ENVELOPE.toString();
		mediaSizeName[39] = MediaSizeName.JAPANESE_DOUBLE_POSTCARD.toString();
		mediaSizeName[40] = MediaSizeName.JAPANESE_POSTCARD.toString();
		mediaSizeName[41] = MediaSizeName.JIS_B0.toString();
		mediaSizeName[42] = MediaSizeName.JIS_B1.toString();
		mediaSizeName[43] = MediaSizeName.JIS_B10.toString();
		mediaSizeName[44] = MediaSizeName.JIS_B2.toString();
		mediaSizeName[45] = MediaSizeName.JIS_B3.toString();
		mediaSizeName[46] = MediaSizeName.JIS_B4.toString();
		mediaSizeName[47] = MediaSizeName.JIS_B5.toString();
		mediaSizeName[48] = MediaSizeName.JIS_B6.toString();
		mediaSizeName[49] = MediaSizeName.JIS_B7.toString();
		mediaSizeName[50] = MediaSizeName.JIS_B8.toString();
		mediaSizeName[51] = MediaSizeName.TABLOID.toString();
		mediaSizeName[52] = MediaSizeName.JIS_B9.toString();
		mediaSizeName[53] = MediaSizeName.LEDGER.toString();
		mediaSizeName[54] = MediaSizeName.MONARCH_ENVELOPE.toString();
		mediaSizeName[55] = MediaSizeName.NA_10X13_ENVELOPE.toString();
		mediaSizeName[56] = MediaSizeName.NA_10X14_ENVELOPE.toString();
		mediaSizeName[57] = MediaSizeName.NA_10X15_ENVELOPE.toString();
		mediaSizeName[58] = MediaSizeName.NA_5X7.toString();
		mediaSizeName[59] = MediaSizeName.NA_6X9_ENVELOPE.toString();
		mediaSizeName[60] = MediaSizeName.NA_7X9_ENVELOPE.toString();
		mediaSizeName[61] = MediaSizeName.NA_8X10.toString();
		mediaSizeName[62] = MediaSizeName.NA_9X11_ENVELOPE.toString();
		mediaSizeName[63] = MediaSizeName.NA_9X12_ENVELOPE.toString();
		mediaSizeName[64] = MediaSizeName.NA_LEGAL.toString();
		mediaSizeName[65] = MediaSizeName.NA_LETTER.toString();
		mediaSizeName[66] = MediaSizeName.NA_NUMBER_10_ENVELOPE.toString();
		mediaSizeName[67] = MediaSizeName.NA_NUMBER_11_ENVELOPE.toString();
		mediaSizeName[68] = MediaSizeName.NA_NUMBER_12_ENVELOPE.toString();
		mediaSizeName[69] = MediaSizeName.NA_NUMBER_14_ENVELOPE.toString();
		mediaSizeName[70] = MediaSizeName.NA_NUMBER_9_ENVELOPE.toString();
		mediaSizeName[71] = MediaSizeName.PERSONAL_ENVELOPE.toString();
		mediaSizeName[72] = MediaSizeName.QUARTO.toString();
		system.setMediaSizeName(mediaSizeName);
		
		String[] mediaTray = new String[8];
		mediaTray[0] = MediaTray.BOTTOM.toString();
		mediaTray[1] = MediaTray.ENVELOPE.toString();
		mediaTray[2] = MediaTray.LARGE_CAPACITY.toString();
		mediaTray[3] = MediaTray.MAIN.toString();
		mediaTray[4] = MediaTray.MANUAL.toString();
		mediaTray[5] = MediaTray.MIDDLE.toString();
		mediaTray[6] = MediaTray.SIDE.toString();
		mediaTray[7] = MediaTray.TOP.toString();
		system.setMediaTray(mediaTray);
		
		String[] multipleDocumentHandling = new String[4];
		multipleDocumentHandling[0] = MultipleDocumentHandling.SEPARATE_DOCUMENTS_COLLATED_COPIES.toString();
		multipleDocumentHandling[1] = MultipleDocumentHandling.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES.toString();
		multipleDocumentHandling[2] = MultipleDocumentHandling.SINGLE_DOCUMENT.toString();
		multipleDocumentHandling[3] = MultipleDocumentHandling.SINGLE_DOCUMENT_NEW_SHEET.toString();
		system.setMultipleDocumentHandling(multipleDocumentHandling);
		
		String[] orientationRequested = new String[4];
		orientationRequested[0] = OrientationRequested.LANDSCAPE.toString();
		orientationRequested[1] = OrientationRequested.PORTRAIT.toString();
		orientationRequested[2] = OrientationRequested.REVERSE_LANDSCAPE.toString();
		orientationRequested[3] = OrientationRequested.REVERSE_PORTRAIT.toString();
		system.setOrientationRequested(orientationRequested);
		
		String[] presentationDirection = new String[8];
		presentationDirection[0] = PresentationDirection.TOBOTTOM_TOLEFT.toString();
		presentationDirection[1] = PresentationDirection.TOBOTTOM_TORIGHT.toString();
		presentationDirection[2] = PresentationDirection.TOLEFT_TOBOTTOM.toString();
		presentationDirection[3] = PresentationDirection.TOLEFT_TOTOP.toString();
		presentationDirection[4] = PresentationDirection.TORIGHT_TOBOTTOM.toString();
		presentationDirection[5] = PresentationDirection.TORIGHT_TOTOP.toString();
		presentationDirection[6] = PresentationDirection.TOTOP_TOLEFT.toString();
		presentationDirection[7] = PresentationDirection.TOTOP_TORIGHT.toString();
		system.setPresentationDirection(presentationDirection);
		
		String[] printQuality = new String[3];
		printQuality[0] = PrintQuality.DRAFT.toString();
		printQuality[1] = PrintQuality.HIGH.toString();
		printQuality[2] = PrintQuality.NORMAL.toString();
		system.setPrintQuality(printQuality);
		
		String[] sheetCollate = new String[2];
		sheetCollate[0] = SheetCollate.COLLATED.toString();
		sheetCollate[0] = SheetCollate.UNCOLLATED.toString();
		system.setSheetCollate(sheetCollate);
		
		String[] sides = new String[5];
		sides[0] = Sides.DUPLEX.toString();
		sides[1] = Sides.ONE_SIDED.toString();
		sides[2] = Sides.TUMBLE.toString();
		sides[3] = Sides.TWO_SIDED_LONG_EDGE.toString();
		sides[4] = Sides.TWO_SIDED_SHORT_EDGE.toString();
		system.setSides(sides);
		
		return system;
	}
	
	@Override
	public List<PrintRequest> listRequest() {
		return printRequestRepository.findAll();
	}
	
	@Override
	public Optional<PrintRequest> listRequest(Long id) {
		return printRequestRepository.findById(id);
	}

	@Override
	public PrintRequest addRequest(PrintRequest pr) {
		return printRequestRepository.saveAndFlush(pr);
	}

	@Override
	public PrintRequest addSalt(PrintRequest pr) {
		if(null != pr && null != pr.getId() && null != pr.getSalt() && !"".equals(pr.getSalt())) {
			Optional<PrintRequest> opr = printRequestRepository.findById(pr.getId());
			opr.ifPresent((tpr) -> {
				tpr.setSalt(pr.getSalt());
				tpr.setStatus(120);
				printRequestRepository.saveAndFlush(opr.get());
			});
		}
		return pr;
	}

	@Override
	public PrintRequest generateDoc(String name) throws Exception {
		name = (null == name || "".equals(name))?"default.pdf":name;
		PrintRequest pr = new PrintRequest();
		String key = "KF3MJ333IU3X11WPO09A";
		pr.setSalt(key);
		byte[] temp = Files.readAllBytes(Paths.get(this.getClass().getResource(name).toURI()));
		pr.setData(CryptographyUtils.encryptAES256(temp, key));
		return pr;
	}
	
	@Override
	public void updatePrintJobsStatus() {
		try {
			JobParameters jp = new JobParametersBuilder()
					.addLong("timestamp", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(job, jp);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
