package a.entity.gus06.file.info.pagenumber.asstring.s;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151006";}
	
	public static final String FAILED_VALUE = "###";


	private Service t;


	public EntityImpl() throws Exception
	{t = Outside.service(this,"gus06.file.info.pagenumber.asstring");}
	
	
	public Object t(Object obj) throws Exception
	{return call(obj);}
	
	
	
	private Object call(Object obj)
	{
		try{return t.t(obj);}
		catch(Exception e)
		{return FAILED_VALUE;}
	}
}
