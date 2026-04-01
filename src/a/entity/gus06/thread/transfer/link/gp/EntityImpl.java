package a.entity.gus06.thread.transfer.link.gp;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161025";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Holder holder = new Holder((G) o[0], (P) o[1]);
		Thread t = new Thread(holder,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	private class Holder implements Runnable
	{
		private G g;
		private P p;
		
		public Holder(G g, P p)
		{
			this.g = g;
			this.p = p;
		}
		
		public void run()
		{
			while(true) {sleep1();checkTransfer(g,p);}
		}
	}
	
	
	
	private void sleep1()
	{
		try{Thread.sleep(1);}
		catch(Exception e)
		{Outside.err(this,"sleep1()",e);}
	}
	
	
	private void checkTransfer(G g, P p)
	{
		try
		{
			Object data = g.g();
			if(data!=null) p.p(data);
		}
		catch(Exception e)
		{Outside.err(this,"checkTransfer(G,P)",e);}
	}
}