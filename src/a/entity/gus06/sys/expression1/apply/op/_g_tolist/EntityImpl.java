package a.entity.gus06.sys.expression1.apply.op._g_tolist;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}
	
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof G) return new T1((G) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private G g;
		public T1(G g) {this.g = g;}
		
		public Object t(Object obj) throws Exception
		{
			int n = toInt(obj);
			List list = new ArrayList();
			for(int i=0;i<n;i++) list.add(g.g());
			return list;
		}
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
