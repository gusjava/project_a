package a.entity.gus06.convert.inputstreamtostring;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190706";}


	private Service isToString;

	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.io.transfer.tostring");
	}
	
	public Object t(Object obj) throws Exception
	{
		return isToString.t(obj);
	}
}
