package a.entity.gus06.file.write.string.autodetect.forceutf8;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160527";}


	private Service isXhtml;
	
	public EntityImpl() throws Exception
	{
		isXhtml = Outside.service(this,"gus06.file.filter.ext.istype.text.xhtml");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(isXhtml.f(obj)) return true;
		return false;
	}
}