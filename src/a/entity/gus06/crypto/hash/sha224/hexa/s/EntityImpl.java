package a.entity.gus06.crypto.hash.sha224.hexa.s;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160302";}
	
	public static final String FAILED_VALUE = "###";


	private Service t;


	public EntityImpl() throws Exception
	{t = Outside.service(this,"gus06.crypto.hash.sha224.hexa");}
	
	
	public Object t(Object obj) throws Exception
	{return call(obj);}
	
	
	
	private Object call(Object obj)
	{
		try{return t.t(obj);}
		catch(Exception e)
		{return FAILED_VALUE;}
	}
}
