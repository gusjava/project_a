package a.entity.gus06.sys.xhtmlparser1.parser;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170226";}


	private Service split;
	private Service regroup;

	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.sys.xhtmlparser1.parser.split");
		regroup = Outside.service(this,"gus06.sys.xhtmlparser1.parser.regroup");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		try
		{
			Object list = split.t(s);
			return regroup.t(list);
		}
		catch(Exception e)
		{
			String message = "Failed to parse XHTML string:\n"+s;
			throw new Exception(message,e);
		}
	}
}