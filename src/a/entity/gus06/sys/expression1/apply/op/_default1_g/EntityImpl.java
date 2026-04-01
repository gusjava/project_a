package a.entity.gus06.sys.expression1.apply.op._default1_g;

import a.framework.*;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160227";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof G) return new Wrap((G) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class Wrap implements T
	{
		private G g;
		
		public Wrap(G g)
		{this.g = g;}
		
		public Object t(Object obj) throws Exception
		{return new G1(g,obj);}
	}
	
	
	private class G1 implements G
	{
		private G g;
		private Object d;
		
		public G1(G g, Object d)
		{
			this.g = g;
			this.d = d;
		}
		public Object g() throws Exception
		{
			Object res = g.g();
			return !isEmpty(res) ? res : d;
		}
	}
	
	
	
	private boolean isEmpty(Object value)
	{
		if(value==null) return true;
		if(value.equals("")) return true;
		if(value instanceof Collection && ((Collection) value).isEmpty()) return true;
		if(value instanceof Map && ((Map) value).isEmpty()) return true;
		return false;
	}
}
