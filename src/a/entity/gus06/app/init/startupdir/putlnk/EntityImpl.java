package a.entity.gus06.app.init.startupdir.putlnk;

import a.framework.*;


public class EntityImpl implements Entity {

	public String creationDate() {return "20180309";}
	
	public static final String KEY = "app.startupdir.putlnk";


	private Service perform;
	private Service propBoolDF;
	

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.lnk.create.shortcut2.appjar.onstartup.ifnotfound");
		propBoolDF = Outside.service(this,"propbool_df");
		
		if(propBoolDF.f(KEY)) perform.e();
	}
}
