package a.entity.gus06.sys.expression1.apply.op._as_g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof G) return new G1((G) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class G1 implements G
	{
		private G g;
		public G1(G g){this.g = g;}
		
		public Object g() throws Exception
		{return g.g();}
	}
}