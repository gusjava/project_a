package a.entity.gus.x.execute.th.delay1s;

import a.framework.*;

public class EntityImpl implements Entity, P
{
	public String creationDate() {return "20260409";}

	public void p(Object obj) throws Exception
	{
		E e = (E) obj;
		new Thread()
		{
			public void run()
			{
				try
				{
					Thread.sleep(1000);
					e.e();
				}
				catch (Exception ex) {Outside.err(EntityImpl.this, "run", ex);}
			}
		}.start();
	}
}
