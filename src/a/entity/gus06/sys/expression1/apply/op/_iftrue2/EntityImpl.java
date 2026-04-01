package a.entity.gus06.sys.expression1.apply.op._iftrue2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160304";}
	
	
	private Service findBoolean;
	
	public EntityImpl() throws Exception
	{findBoolean = Outside.service(this,"gus06.find.boolean1");}
	
	private boolean toBoolean(Object obj) throws Exception
	{return ((Boolean) findBoolean.t(obj)).booleanValue();}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Boolean) return new T1(toBoolean(obj));
		if(obj instanceof Integer) return new T1(toBoolean(obj));
		if(obj instanceof String) return new T1(toBoolean(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private boolean value;
		
		public T1(boolean value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return value ? obj : "";}
	}
}
