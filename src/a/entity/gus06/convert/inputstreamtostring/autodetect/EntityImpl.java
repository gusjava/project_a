package a.entity.gus06.convert.inputstreamtostring.autodetect;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250510";}


	private Service isToString;

	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
	}
	
	public Object t(Object obj) throws Exception
	{
		return isToString.t(obj);
	}
}