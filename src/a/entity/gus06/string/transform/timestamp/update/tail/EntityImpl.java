package a.entity.gus06.string.transform.timestamp.update.tail;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231014";}


	private Service now;
	private Service endsWithTStamp;


	public EntityImpl() throws Exception
	{
		now = Outside.service(this,"gus06.time.now");
		endsWithTStamp = Outside.service(this,"gus06.filter.string.timestamp.endswith");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String str = (String) obj;
		if(endsWithTStamp.f(str)) str = str.substring(0, str.length()-15);
		while(str.endsWith("_")) str = str.substring(0, str.length()-1);
		return str+"_"+now.g();
	}
}