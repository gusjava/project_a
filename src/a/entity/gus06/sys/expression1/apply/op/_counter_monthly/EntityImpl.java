package a.entity.gus06.sys.expression1.apply.op._counter_monthly;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190724";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.app.persister1.counter.monthly");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return find(obj);
		if(obj instanceof Number) return find(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Integer find(Object obj) throws Exception
	{
		return toInt(perform.r(""+obj));
	}
	
	
	private Integer toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return (Integer) obj;
		if(obj instanceof String) return Integer.valueOf((String) obj);
		
		throw new Exception("Invalid output data type: "+obj.getClass().getName());
	}
}