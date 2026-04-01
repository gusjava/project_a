package a.entity.gus06.string.transform.timestamp.update.head;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151004";}


	private Service now;
	private Service startsWithTStamp;


	public EntityImpl() throws Exception
	{
		now = Outside.service(this,"gus06.time.now");
		startsWithTStamp = Outside.service(this,"gus06.filter.string.timestamp.startswith");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String str = (String) obj;
		if(startsWithTStamp.f(str)) str = str.substring(15);
		while(str.startsWith("_")) str = str.substring(1);
		return now.g()+"_"+str;
	}
}