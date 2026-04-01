package a.entity.gus06.sys.expression1.apply.op._keylist_vsortby_inv;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220628";}


	private Service builder;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder2.t");
		perform = Outside.service(this,"gus06.data.perform.keylist.vsortby.inv");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof Map) return new T1(value,opMap);
		if(value instanceof File) return new T1(value,opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		private Map opMap;
		
		public T1(Object value, Map opMap)
		{
			this.value = value;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{value,toT(obj)});}
		
		private T toT(Object obj) throws Exception
		{return (T) builder.t(new Object[]{obj,opMap});}
	}
}