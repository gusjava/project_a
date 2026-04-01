package a.entity.gus06.feature.force.i;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}

	
	
	public Object t(Object obj) throws Exception
	{return new I1((I) obj);}
	
	
	private class I1 implements I
	{
		private I i;
		public I1(I i) {this.i = i;}
		
		public Object i() throws Exception
		{
			while(true)
			{
				try
				{
					return i.i();
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