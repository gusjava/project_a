package a.entity.gus06.file.read.string.from.properties;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150620";}


	private Service textFromProp;


	public EntityImpl() throws Exception
	{
		textFromProp = Outside.service(this,"gus06.file.properties.filetostring");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return textFromProp.t(obj);
	}
}
