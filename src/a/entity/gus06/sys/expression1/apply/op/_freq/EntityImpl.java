package a.entity.gus06.sys.expression1.apply.op._freq;

import a.framework.*;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof List) return new T_freq((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T_freq implements T
	{
		private List list;
		public T_freq(List list) {this.list = list;}
		
		public Object t(Object obj) throws Exception
		{
			int n = Collections.frequency(list,obj);
			return Integer.valueOf(n);
		}
	}
}
