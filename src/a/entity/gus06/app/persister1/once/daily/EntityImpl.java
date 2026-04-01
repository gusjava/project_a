package a.entity.gus06.app.persister1.once.daily;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, F, V {

	public String creationDate() {return "20170320";}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");


	private Service persister1;

	public EntityImpl() throws Exception
	{
		persister1 = Outside.service(this,"gus06.app.persister1.once");
	}
	
	public void v(String key, Object obj) throws Exception
	{
		String key1 = toKey1(key);
		persister1.v(key1, obj);
	}
	
	public boolean f(Object obj) throws Exception
	{
		String key = toKey1(""+obj);
		return persister1.f(key);
	}
	
	private String toKey1(String key)
	{return getClass().getName()+"_"+key+"_"+today();}
	
	private String today()
	{return sdf.format(new Date());}
}