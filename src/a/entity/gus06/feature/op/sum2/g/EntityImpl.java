package a.entity.gus06.feature.op.sum2.g;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180302";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List gg = (List) o[0];
		T sumT = (T) o[1];
		
		return new G1(gg,sumT);
	}
	
	
	private class G1 implements G
	{
		private List gg;
		private T sumT;
		
		public G1(List gg, T sumT)
		{
			this.gg = gg;
			this.sumT = sumT;
		}
		
		public Object g() throws Exception
		{
			Object[] r = new Object[gg.size()];
			for(int i=0;i<gg.size();i++) r[i] = ((G) gg.get(i)).g();
			return sumT.t(r);
		}
	}
}
