package a.entity.gus06.sys.filemanagement1.gui.gui1_4.analyze.export.combo;

import a.framework.*;
import javax.swing.JComboBox;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, G {

	public String creationDate() {return "20210131";}


	private Service custWhite;
	
	private Service exportMd5Listing;
	private Service exportEbookData;
	private Service exportEbookData1;
	private Service exportPdfData;
	private Service exportPdfData1;
	private Service exportVideoData;
	private Service exportVideoData1;
	private Service exportVideoAllocine;
	private Service exportAllocineData;
	
	private JComboBox combo;
	private List services;
	
	

	public EntityImpl() throws Exception
	{
		custWhite = Outside.service(this,"gus06.swing.combobox.cust.white");
		
		exportMd5Listing = Outside.service(this,"gus06.sys.filemanagement1.analyze.export.md5.listing");
		exportEbookData = Outside.service(this,"gus06.sys.filemanagement1.analyze.export.ebook.data");
		exportEbookData1 = Outside.service(this,"gus06.sys.filemanagement1.analyze.export.ebook.data1");
		exportPdfData = Outside.service(this,"gus06.sys.filemanagement1.analyze.export.pdf.data");
		exportPdfData1 = Outside.service(this,"gus06.sys.filemanagement1.analyze.export.pdf.data1");
		exportVideoData = Outside.service(this,"gus06.sys.filemanagement1.analyze.export.video.data");
		exportVideoData1 = Outside.service(this,"gus06.sys.filemanagement1.analyze.export.video.data1");
		exportVideoAllocine = Outside.service(this,"gus06.sys.filemanagement1.analyze.export.video.allocine");
		exportAllocineData = Outside.service(this,"gus06.sys.filemanagement1.analyze.export.allocine.data");
		
		
		combo = new JComboBox();
		services = new ArrayList();
		custWhite.p(combo);
		
		add(exportMd5Listing,"Export md5 listing");
		add(exportEbookData,"Export ebook data");
		add(exportEbookData1,"Export ebook data (infos)");
		add(exportPdfData,"Export PDF data");
		add(exportPdfData1,"Export PDF data (infos)");
		add(exportVideoData,"Export video data");
		add(exportVideoData1,"Export video data (infos)");
		add(exportVideoAllocine,"Export video allocine");
		add(exportAllocineData,"Export allocine data");
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