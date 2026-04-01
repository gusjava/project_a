package a.entity.gus06.sys.expression1.apply.op._extend_west;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220530";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.feature.extend.gui.west");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		if(value==null) return null;
		
		return new T1(value);
	}
	
	
	private class T1 implements T
	{
		private Object value;
		
		public T1(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{value,obj});}
	}
}