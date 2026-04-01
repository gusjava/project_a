package a.entity.gus06.sys.filemanagement1.gui.gui1_4.analyze.external.combo;

import a.framework.*;
import javax.swing.JComboBox;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, G {

	public String creationDate() {return "20230326";}


	private Service custWhite;
	
	private Service dirFilesKnown;
	private Service dirFilesUnknown;
	
	private JComboBox combo;
	private List services;
	
	

	public EntityImpl() throws Exception
	{
		custWhite = Outside.service(this,"gus06.swing.combobox.cust.white");
		
		dirFilesKnown = Outside.service(this,"gus06.sys.filemanagement1.analyze.external.directory.files.known");
		dirFilesUnknown = Outside.service(this,"gus06.sys.filemanagement1.analyze.external.directory.files.unknown");
		
		
		combo = new JComboBox();
		services = new ArrayList();
		custWhite.p(combo);
		
		add(dirFilesKnown, "Search for known files");
		add(dirFilesUnknown, "Search for unknown files");
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