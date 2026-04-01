package a.entity.gus06.sys.filemanagement1.gui.gui3_7.name0;

import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201105";}

	public static final String FIELDNAME = "name0_md5";

	private Service tabGui;
	
	public EntityImpl() throws Exception
	{
		tabGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.gui3.tab");
		tabGui.v("fieldName",FIELDNAME);
	}
	
	public Object i() throws Exception
	{return tabGui.i();}
	
	public void p(Object obj) throws Exception
	{tabGui.p(obj);}
}