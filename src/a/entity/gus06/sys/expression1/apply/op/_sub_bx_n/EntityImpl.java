package a.entity.gus06.sys.expression1.apply.op._sub_bx_n;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180513";}
	
	public final static String DELIM = "\n";


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.substr.before.position");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			return perform.t(new Object[]{data,DELIM,obj});
		}
	}
}
