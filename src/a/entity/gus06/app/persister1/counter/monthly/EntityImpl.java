package a.entity.gus06.app.persister1.counter.monthly;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, R, V {

	public String creationDate() {return "20190724";}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");


	private Service persister1;

	public EntityImpl() throws Exception
	{
		persister1 = Outside.service(this,"gus06.app.persister1.counter");
	}
	
	public void v(String key, Object obj) throws Exception
	{
		String key1 = toKey1(key);
		persister1.v(key1, obj);
	}
	
	public Object r(String key) throws Exception
	{
		String key1 = toKey1(key);
		return persister1.r(key1);
	}
	
	private String toKey1(String key)
	{return getClass().getName()+"_"+key+"_"+thisMonth();}
	
	private String thisMonth()
	{return sdf.format(new Date());}
}