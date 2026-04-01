package a.entity.gus06.sys.expression1.apply.op._bool_to_bipolar;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180114";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.buildholder.bool");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Boolean) return perform((Boolean) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Integer perform(Boolean b)
	{
		int n = b.booleanValue() ? 1 : -1;
		return Integer.valueOf(n);
	}
}
