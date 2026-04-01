package a.entity.gus06.feature.thread.pg;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20150623";}


	private HashMap map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		G g = (G) o[1];
		
		return new Runnable1(p,g);
	}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		G g = (G) o[1];
		
		if(isRunning(p)) return;
		
		Runnable r = new Runnable1(p,g);
		Thread t = new Thread(r,"THREAD_"+getClass().getName());
		map.put(p,t);
		t.start();
	}
	
	
	
	
	
	private boolean isRunning(P p)
	{
		if(!map.containsKey(p)) return false;
		Thread t = (Thread) map.get(p);
		return t.isAlive();
	}
	
	
	private void perform(P p, G g)
	{
		try{p.p(g.g());}
		catch(Exception e)
		{Outside.err(this,"perform(P,G)",e);}
	}
	
	
	private class Runnable1 implements Runnable
	{
		private P p;
		private G g;
		
		public Runnable1(P p, G g)
		{
			this.p = p;
			this.g = g;
		}
		public void run() {perform(p,g);}
	}
}
