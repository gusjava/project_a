package a.entity.gus06.sys.filemanagement1.gui.gui2_3.genres;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201014";}
	
	public static final String FIELDNAME = "genre_code";

	private Service tabGui;
	
	public EntityImpl() throws Exception
	{
		tabGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui2.tab");
		tabGui.v("fieldName",FIELDNAME);
	}
	
	public Object i() throws Exception
	{return tabGui.i();}
	
	public void p(Object obj) throws Exception
	{tabGui.p(obj);}
}