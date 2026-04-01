package a.entity.gus06.feature.force.r;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}

	
	
	public Object t(Object obj) throws Exception
	{return new R1((R) obj);}
	
	
	private class R1 implements R
	{
		private R r;
		public R1(R r) {this.r = r;}
		
		public Object r(String key) throws Exception
		{
			while(true)
			{
				try
				{
					return r.r(key);
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