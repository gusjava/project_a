package a.entity.gus06.sys.expression1.apply.op._g_dne;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240202";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof G) return new T1((G) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class T1 implements T
	{
		private G g;
		public T1(G g) {this.g = g;}
		
		public Object t(Object obj) throws Exception
		{return new G1(g, obj);}
	}
	
	private class G1 implements G
	{
		private G g;
		private Object defaultValue;
		
		public G1(G g, Object defaultValue)
		{
			this.g = g;
			this.defaultValue = defaultValue;
		}
		
		public Object g() throws Exception
		{
			try
			{
				Object value = g.g();
				return value!=null ? value : defaultValue;
			}
			catch(Exception e)
			{return defaultValue;}
		}
	}
}