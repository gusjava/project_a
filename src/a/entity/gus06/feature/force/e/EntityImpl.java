package a.entity.gus06.feature.force.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}

	
	
	public Object t(Object obj) throws Exception
	{return new E1((E) obj);}
	
	
	private class E1 implements E
	{
		private E e;
		public E1(E e) {this.e = e;}
		
		public void e() throws Exception
		{
			while(true)
			{
				try
				{
					e.e();
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