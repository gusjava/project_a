package a.entity.gus06.tostring.desc.array2;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}


	private Service short1;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		short1 = Outside.service(this,"gus06.tostring.desc.short1");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}


	public Object t(Object obj) throws Exception
	{
		return short1.t(obj)+" "+toString.t(obj);
	}
}
