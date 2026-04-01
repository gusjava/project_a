package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.string;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}


	private Service handle;

	public EntityImpl() throws Exception
	{
		handle = Outside.service(this,"gus06.tostring.tostring1");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		StringBuffer b = new StringBuffer();
		for(Object o:oo) b.append(toString(o));
		return b.toString();
	}
	
	private String toString(Object o) throws Exception
	{return (String) handle.t(o);}
}
