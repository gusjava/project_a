package a.entity.gus06.string.transform.clipboard.pastecopy;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160517";}


	private Service clipboard;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
	}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String c = (String) clipboard.g();
		clipboard.p(s);
		return c;
	}
}
