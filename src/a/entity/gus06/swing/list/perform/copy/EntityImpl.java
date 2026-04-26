package a.entity.gus06.swing.list.perform.copy;

import a.framework.*;
import javax.swing.JList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160603";}


	private Service clipboardAccess;
	private Service jlistToString;


	public EntityImpl() throws Exception
	{
		clipboardAccess = Outside.service(this,"gus.x.clipboard.string");
		jlistToString = Outside.service(this,"gus06.tostring.jlist");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		String s = (String) jlistToString.t(list);
		clipboardAccess.p(s);
	}
}
