package a.entity.gus06.sys.expression1.apply.op._sortd;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180504";}


	private Service perform;
	private Service findList;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.sortd");
		findList = Outside.service(this,"gus06.find.list");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		
		if(value instanceof List) return new T1(value);
		if(value instanceof Set) return new T1(findList.t(value));
		if(value instanceof Object[]) return new T1(findList.t(value));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		
		public T1(Object data)
		{this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{data,obj});}
	}
}
