package a.entity.gus06.sys.filemanagement1.gui.gui1_4.analyze.search.combo;

import a.framework.*;
import javax.swing.JComboBox;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, G {

	public String creationDate() {return "20201013";}


	private Service custWhite;
	
	private Service searchMissingProp;
	private Service searchMissingPreview;
	
	private Service searchMissingAllocineProp;
	private Service searchMissingAllocinePoster;
	private Service searchMissingAllocineMd5Code;
	
	private Service searchInvalidProp;
	private Service searchInvalidPreview;
	
	private Service searchErrorProp;
	
	private Service searchEmptyFiles;
	private Service searchFailedMd5;
	
	private Service searchDoubloons1;
	
	private JComboBox combo;
	private List services;
	
	

	public EntityImpl() throws Exception
	{
		custWhite = Outside.service(this,"gus06.swing.combobox.cust.white");
		
		searchMissingProp = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.missing.prop");
		searchMissingPreview = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.missing.preview");
		
		searchMissingAllocineProp = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.missing.allocine.prop");
		searchMissingAllocinePoster = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.missing.allocine.poster");
		searchMissingAllocineMd5Code = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.missing.allocine.md5code");
		
		searchInvalidProp = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.invalid.prop");
		searchInvalidPreview = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.invalid.preview");
		
		searchErrorProp = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.error.prop");
		
		searchEmptyFiles = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.emptyfiles");
		searchFailedMd5 = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.failedmd5");
		
		searchDoubloons1 = Outside.service(this,"gus06.sys.filemanagement1.analyze.search.doubloons1");
		
		
		combo = new JComboBox();
		services = new ArrayList();
		custWhite.p(combo);
		
		add(searchMissingProp,			"Search for missing properties");
		add(searchMissingPreview,		"Search for missing previews");
		
		add(searchMissingAllocineProp,		"Search for missing allocine properties");
		add(searchMissingAllocinePoster,	"Search for missing allocine posters");
		add(searchMissingAllocineMd5Code,	"Search for missing allocine md5-allocine relations");
		
		add(searchInvalidProp,			"Search for invalid properties");
		add(searchInvalidPreview,		"Search for invalid previews");
		
		add(searchErrorProp,			"Search for error properties");
		
		add(searchEmptyFiles,			"Search for empty files");
		add(searchFailedMd5,			"Search for failed MD5");
		
		add(searchDoubloons1,			"Search for doubloon files");
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
