package a.entity.gus06.command.print.entity.stringconsts;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140827";}


	private Service retrieveSrcCode;
	private Service extractConst;
	private Service checkName;
	private Service printObject;
	
	
	public EntityImpl() throws Exception
	{
		retrieveSrcCode = Outside.service(this,"gus06.entitydev.retrieve.srccode1");
		extractConst = Outside.service(this,"gus06.java.srccode.extract.entity.constants.string");
		checkName = Outside.service(this,"gus06.entitydev.entityname.check.existing");
		printObject = Outside.service(this,"gus06.print.object");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		String entityName = (String) checkName.t(obj);
		String src = (String) retrieveSrcCode.t(entityName);
		Object result = extractConst.t(src);
		printObject.p(result);
	}
}
