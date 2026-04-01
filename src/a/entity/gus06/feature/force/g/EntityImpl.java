package a.entity.gus06.feature.force.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}

	
	
	public Object t(Object obj) throws Exception
	{return new G1((G) obj);}
	
	
	private class G1 implements G
	{
		private G g;
		public G1(G g) {this.g = g;}
		
		public Object g() throws Exception
		{
			while(true)
			{
				try
				{
					return g.g();
				}
				catch(Exception ex)
				{
					sleep1();
				}
			}
		}
	}
	
	
	private void sleep1()
	{
		try{Thread.sleep(1);}
		catch(InterruptedException e){}
	}
}