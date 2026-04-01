package a.entity.gus06.feature.op.sum.g.nnn;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}

	
	public Object t(Object obj) throws Exception
	{return new G1((G[]) obj);}
	
	
	private class G1 implements G
	{
		private G[] gg;
		public G1(G[] gg){this.gg = gg;}
		
		public Object g() throws Exception
		{
			for(G g:gg)
			{
				Object r = g.g();
				if(r!=null) return r; 
			}
			return null;
		}
	}
}
