package a.entity.gus06.string.transform.clipboard.paste.after;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160517";}


	private Service clipboard;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
	}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String c = (String) clipboard.g();
		return s+c;
	}
}
