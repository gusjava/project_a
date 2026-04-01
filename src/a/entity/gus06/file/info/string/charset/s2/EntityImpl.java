package a.entity.gus06.file.info.string.charset.s2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170403";}


	private Service t;


	public EntityImpl() throws Exception
	{t = Outside.service(this,"gus06.file.info.string.charset");}
	
	
	public Object t(Object obj) throws Exception
	{return call(obj);}
	
	
	
	private Object call(Object obj)
	{
		try{return t.t(obj);}
		catch(Exception e)
		{return null;}
	}
}
