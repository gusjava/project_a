package a.entity.gus06.sys.expression1.apply.op._sortdby;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180504";}


	private Service builder;
	private Service perform;
	private Service findList;
	
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.t");
		perform = Outside.service(this,"gus06.data.perform.sortdby");
		findList = Outside.service(this,"gus06.find.list");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof List) return new T1(value,opMap);
		if(value instanceof Set) return new T1(findList.t(value),opMap);
		if(value instanceof Object[]) return new T1(findList.t(value),opMap);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		private Map opMap;
		
		public T1(Object data, Map opMap)
		{
			this.data = data;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{return new T2(data,toT(obj));}
		
		private T toT(Object obj) throws Exception
		{return (T) builder.t(new Object[]{obj,opMap});}
	}
	
	
	private class T2 implements T
	{
		private Object data;
		private T t;
		
		public T2(Object data, T t)
		{
			this.data = data;
			this.t = t;
		}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{data,t,obj});}
	}
}
