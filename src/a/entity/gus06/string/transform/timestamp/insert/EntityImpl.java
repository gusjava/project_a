package a.entity.gus06.string.transform.timestamp.insert;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180909";}


	private Service now;
	private Service pattern;


	public EntityImpl() throws Exception
	{
		now = Outside.service(this,"gus06.time.now");
		pattern = Outside.service(this,"gus06.time.now.pattern");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String str = (String) obj;
		if(str==null || str.trim().equals("")) return now.g();
		return pattern.t(str);
	}
}
