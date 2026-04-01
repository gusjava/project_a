package a.entity.gus06.sys.expression1.apply.op._lines_tab_set;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190703";}
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.lines.tab.setnb");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private String value;
		public T1(String value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return value;
			return perform.t(new Object[]{value,toInt(obj)});
		}
		
		private int toInt(Object obj) throws Exception
		{
			if(obj instanceof Number) return ((Number) obj).intValue();
			if(obj instanceof String) return Integer.parseInt((String) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
}
