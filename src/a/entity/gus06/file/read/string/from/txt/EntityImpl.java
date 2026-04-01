package a.entity.gus06.file.read.string.from.txt;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150617";}


	private Service textFromTxt;


	public EntityImpl() throws Exception
	{
		textFromTxt = Outside.service(this,"gus06.file.read.string.autodetect");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return textFromTxt.t(obj);
	}
}
