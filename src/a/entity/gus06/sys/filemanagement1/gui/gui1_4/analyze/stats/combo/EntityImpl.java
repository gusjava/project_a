package a.entity.gus06.sys.filemanagement1.gui.gui1_4.analyze.stats.combo;

import a.framework.*;
import javax.swing.JComboBox;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, G {

	public String creationDate() {return "20201014";}


	private Service custWhite;
	
	private Service statsErrorProp;
	private Service statsAllocineFields;
	private Service statsVideoFields;
	private Service statsEbookFields;
	private Service statsPdfFields;
	private Service statsPropMime;
	private Service statsPropFileType;
	
	private JComboBox combo;
	private List services;
	
	

	public EntityImpl() throws Exception
	{
		custWhite = Outside.service(this,"gus06.swing.combobox.cust.white");
		
		statsErrorProp = Outside.service(this,"gus06.sys.filemanagement1.analyze.stats.error.prop");
		statsAllocineFields = Outside.service(this,"gus06.sys.filemanagement1.analyze.stats.allocine.fields");
		statsVideoFields = Outside.service(this,"gus06.sys.filemanagement1.analyze.stats.video.fields");
		statsEbookFields = Outside.service(this,"gus06.sys.filemanagement1.analyze.stats.ebook.fields");
		statsPdfFields = Outside.service(this,"gus06.sys.filemanagement1.analyze.stats.pdf.fields");
		statsPropMime = Outside.service(this,"gus06.sys.filemanagement1.analyze.stats.prop.mime");
		statsPropFileType = Outside.service(this,"gus06.sys.filemanagement1.analyze.stats.prop.filetype");
		
		
		combo = new JComboBox();
		services = new ArrayList();
		custWhite.p(combo);
		
		add(statsErrorProp,"Error prop stats");
		add(statsAllocineFields,"Allocine fields stats");
		add(statsVideoFields,"Video fields stats");
		add(statsEbookFields,"Ebook fields stats");
		add(statsPdfFields,"PDF fields stats");
		add(statsPropMime,"Prop MIME stats");
		add(statsPropFileType,"Prop file type stats");
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