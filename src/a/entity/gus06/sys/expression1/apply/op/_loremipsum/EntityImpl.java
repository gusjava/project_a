package a.entity.gus06.sys.expression1.apply.op._loremipsum;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231101";}

	private Service generate;
	
	public EntityImpl() throws Exception
	{
		generate = Outside.service(this,"gus06.data.generate.string.loremipsum");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		if(obj instanceof Integer) return generate.t(obj);
		return generate.g();
	}
}