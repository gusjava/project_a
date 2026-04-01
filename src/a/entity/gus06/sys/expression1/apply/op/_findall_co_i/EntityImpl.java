package a.entity.gus06.sys.expression1.apply.op._findall_co_i;

import a.framework.*;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151124";}


	private Service builder;
	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.filter.string.build.contains_i");
		perform = Outside.service(this,"gus06.data.perform.findall");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return new T1(obj);
		if(obj instanceof Set) return new T1(obj);
		if(obj instanceof Object[]) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{data,toF(obj)});}
		
		private F toF(Object obj) throws Exception
		{return (F) builder.t(obj);}
	}
}
