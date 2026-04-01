package a.entity.gus06.sys.expression1.apply.op._mult_tolist;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161215";}
	
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new T1(obj);
	}
	
	
	
	private class T1 implements T
	{
		private Object value;
		
		public T1(Object value)
		{this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			int n = toInt(obj);
			List list = new ArrayList();
			for(int i=0;i<n;i++) list.add(value);
			return list;
		}
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
