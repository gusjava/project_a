package a.entity.gus06.sys.expression1.apply.op._append_seq_point;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190717";}

	public static final String GLUE = ".";
	
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.append.seq");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return new T1(null);
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private String s;

		public T1(String s)
		{this.s = s;}
		
		public Object t(Object obj) throws Exception
		{
			String element = (String) obj;
			return perform.t(new String[]{s,GLUE,element});
		}
	}
}
