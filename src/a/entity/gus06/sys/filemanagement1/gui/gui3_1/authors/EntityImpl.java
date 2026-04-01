package a.entity.gus06.sys.filemanagement1.gui.gui3_1.authors;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201103";}

	public static final String FIELDNAME = "author_md5";
	public static final String PROPNAME = "ebook.author";

	private Service tabGui;
	
	public EntityImpl() throws Exception
	{
		tabGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3.tab");
		tabGui.v("fieldName",FIELDNAME);
		tabGui.v("propName",PROPNAME);
	}
	
	public Object i() throws Exception
	{return tabGui.i();}
	
	public void p(Object obj) throws Exception
	{tabGui.p(obj);}
}