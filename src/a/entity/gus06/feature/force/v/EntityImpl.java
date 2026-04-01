package a.entity.gus06.feature.force.v;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}

	
	
	public Object t(Object obj) throws Exception
	{return new V1((V) obj);}
	
	
	private class V1 implements V
	{
		private V v;
		public V1(V v) {this.v = v;}
		
		public void v(String key, Object obj) throws Exception
		{
			while(true)
			{
				try
				{
					v.v(key,obj);
					return;
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