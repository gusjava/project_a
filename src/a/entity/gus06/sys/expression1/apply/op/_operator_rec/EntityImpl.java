package a.entity.gus06.sys.expression1.apply.op._operator_rec;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160316";}


	private Service mapToT;

	public EntityImpl() throws Exception
	{
		mapToT = Outside.service(this,"gus06.convert.maptot");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof T) return new Recorder(opMap,(T) value);
		if(value instanceof Map) return new Recorder(opMap,(T) mapToT.t(value));
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private class Recorder implements T
	{
		private Map opMap;
		private T t;
		
		public Recorder(Map opMap, T t)
		{
			this.opMap = opMap;
			this.t = t;
		}
		
		public Object t(Object obj) throws Exception
		{
			String name = (String) obj;
			if(!name.startsWith("_")) name = "_"+name;
			return new E1(opMap,name,t);
		}
	}
	
	
	
	private class E1 implements E
	{
		private Map opMap;
		private String name;
		private T t;
		
		public E1(Map opMap, String name, T t)
		{
			this.opMap = opMap;
			this.name = name;
			this.t = t;
		}
		
		public void e() throws Exception
		{
			opMap.put(name,new Op(t));
		}
	}
	
	
	
	
	
	private class Op implements T
	{
		private T t;
		public Op(T t) {this.t = t;}
		
		public Object t(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
			return t.t(o[0]);
		}
	}
}
