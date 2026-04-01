package a.entity.gus06.feature.op.loop.g;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		int n = toInt(o[1]);
		
		return new G1(g,n);
	}
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer)obj).intValue();
		if(obj instanceof String) return Integer.parseInt((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class G1 implements G
	{
		private G g;
		private int n;
		
		public G1(G g, int n)
		{
			this.g = g;
			this.n = n;
		}
		
		public Object g() throws Exception
		{
			List list = new ArrayList();
			for(int i=0;i<n;i++) list.add(g.g());
			return list;
		}
	}
}
