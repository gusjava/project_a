package a.entity.gus06.crypto.hash.md5.hexa.s;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151002";}
	
	public static final String FAILED_VALUE = "###";


	private Service t;


	public EntityImpl() throws Exception
	{t = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");}
	
	
	public Object t(Object obj) throws Exception
	{return call(obj);}
	
	
	
	private Object call(Object obj)
	{
		try{return t.t(obj);}
		catch(Exception e)
		{return FAILED_VALUE;}
	}
}
