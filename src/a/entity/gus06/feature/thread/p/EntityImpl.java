package a.entity.gus06.feature.thread.p;

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
		Object data = o[1];
		
		return new Runnable1(p,data);
	}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		Object data = o[1];
		
		if(isRunning(p)) return;
		
		Runnable r = new Runnable1(p,data);
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
	
	
	private void perform(P p, Object data)
	{
		try{p.p(data);}
		catch(Exception e)
		{Outside.err(this,"perform(P,Object)",e);}
	}
	
	
	private class Runnable1 implements Runnable
	{
		private P p;
		private Object data;
		
		public Runnable1(P p, Object data)
		{
			this.p = p;
			this.data = data;
		}
		public void run() {perform(p,data);}
	}
}
