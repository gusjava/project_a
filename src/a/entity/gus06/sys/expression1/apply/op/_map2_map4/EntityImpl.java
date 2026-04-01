package a.entity.gus06.sys.expression1.apply.op._map2_map4;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190325";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.map.map2tomap4");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map) return new T1((Map) obj);
		if(obj instanceof Map[]) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Map value;
		public T1(Map value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Map[]{value,(Map) obj});}
	}
}
