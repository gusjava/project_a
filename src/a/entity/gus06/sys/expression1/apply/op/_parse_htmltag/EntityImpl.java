package a.entity.gus06.sys.expression1.apply.op._parse_htmltag;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170404";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.xhtmlparser1.analyze2.parsevalue");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
