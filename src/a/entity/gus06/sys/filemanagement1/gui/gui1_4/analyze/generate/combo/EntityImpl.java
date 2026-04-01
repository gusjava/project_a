package a.entity.gus06.sys.filemanagement1.gui.gui1_4.analyze.generate.combo;

import a.framework.*;
import javax.swing.JComboBox;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, G {

	public String creationDate() {return "20201007";}


	private Service custWhite;
	
	private Service generateAllocineData;
	private Service generateAllocineDataActor;
	private Service generateAllocineDataDirector;
	private Service generateAllocineDataGenre;
	private Service generateAllocineDataType;
	private Service generateAllocineDataNationality;
	private Service generateAllocineDataReleaseDate;
	private Service generateAllocineDataProductionYear;
	private Service generateAllocineDataPressRating;
	private Service generateAllocineDataUserRating;
	private Service generateAllocineDataTitle;
	
	private Service generateEbookData;
	private Service generateEbookDataAuthor;
	
	private Service generatePdfData;
	private Service generatePdfDataAuthors;
	private Service generatePdfDataCreator;
	private Service generatePdfDataIsbn;
	private Service generatePdfDataName0;
	private Service generatePdfDataProducer;
	private Service generatePdfDataSubject;
	private Service generatePdfDataTitle;
	
	private JComboBox combo;
	private List services;
	
	

	public EntityImpl() throws Exception
	{
		custWhite = Outside.service(this,"gus06.swing.combobox.cust.white");
		
		generateAllocineData = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data");
		generateAllocineDataActor = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.actor");
		generateAllocineDataDirector = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.director");
		generateAllocineDataGenre = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.genre");
		generateAllocineDataType = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.movietype");
		generateAllocineDataNationality = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.nationality");
		generateAllocineDataReleaseDate = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.releasedate");
		generateAllocineDataProductionYear = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.productionyear");
		generateAllocineDataPressRating = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.pressrating");
		generateAllocineDataUserRating = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.userrating");
		generateAllocineDataTitle = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.allocine.data.title");
	
		generateEbookData = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.ebook.data");
		generateEbookDataAuthor = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.ebook.data.author");
		
		generatePdfData = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.pdf.data");
		generatePdfDataAuthors = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.pdf.data.authors");
		generatePdfDataCreator = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.pdf.data.creator");
		generatePdfDataIsbn = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.pdf.data.isbn");
		generatePdfDataName0 = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.pdf.data.name0");
		generatePdfDataProducer = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.pdf.data.producer");
		generatePdfDataSubject = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.pdf.data.subject");
		generatePdfDataTitle = Outside.service(this,"gus06.sys.filemanagement1.analyze.generate.pdf.data.title");
		
		
		combo = new JComboBox();
		services = new ArrayList();
		custWhite.p(combo);
		
		add(generateAllocineData,"Generate Allocine data (all)");
		add(generateAllocineDataActor,"Generate Allocine data (actors)");
		add(generateAllocineDataDirector,"Generate Allocine data (directors)");
		add(generateAllocineDataGenre,"Generate Allocine data (genres)");
		add(generateAllocineDataType,"Generate Allocine data (movie types)");
		add(generateAllocineDataNationality,"Generate Allocine data (nationalities)");
		add(generateAllocineDataReleaseDate,"Generate Allocine data (release dates)");
		add(generateAllocineDataProductionYear,"Generate Allocine data (production years)");
		add(generateAllocineDataPressRating,"Generate Allocine data (press rating)");
		add(generateAllocineDataUserRating,"Generate Allocine data (user rating)");
		add(generateAllocineDataTitle,"Generate Allocine data (titles)");
	
		add(generateEbookData,"Generate Ebook data (all)");
		add(generateEbookDataAuthor,"Generate Ebook data (author)");
		
		add(generatePdfData,"Generate PDF data (all)");
		add(generatePdfDataAuthors,"Generate PDF data (authors)");
		add(generatePdfDataCreator,"Generate PDF data (creator)");
		add(generatePdfDataIsbn,"Generate PDF data (ISBN)");
		add(generatePdfDataName0,"Generate PDF data (file name)");
		add(generatePdfDataProducer,"Generate PDF data (producer)");
		add(generatePdfDataSubject,"Generate PDF data (subject)");
		add(generatePdfDataTitle,"Generate PDF data (title)");
	}
	
	
	private void add(Service service, String display)
	{
		services.add(service);
		combo.addItem(display);
	}
	
	
	public Object i() throws Exception
	{return combo;}
	
	
	public Object g() throws Exception
	{
		int index = combo.getSelectedIndex();
		return index==-1 ? null : services.get(index);
	}
}