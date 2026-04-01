package a.entity.gus06.feature.op.sum.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170215";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G[] gg = (G[]) o[0];
		T sumT = (T) o[1];
		
		return new G1(gg,sumT);
	}
	
	
	private class G1 implements G
	{
		private G[] gg;
		private T sumT;
		
		public G1(G[] gg, T sumT)
		{
			this.gg = gg;
			this.sumT = sumT;
		}
		
		public Object g() throws Exception
		{
			Object[] r = new Object[gg.length];
			for(int i=0;i<gg.length;i++) r[i] = gg[i].g();
			return sumT.t(r);
		}
	}
}
