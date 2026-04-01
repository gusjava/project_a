package a.entity.gus06.feature.thread.e;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20150625";}


	private HashMap map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		E e = (E) obj;
		return new Runnable1(e);
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		E e = (E) obj;
		
		if(isRunning(e)) return;
		
		Runnable r = new Runnable1(e);
		Thread t = new Thread(r,"THREAD_"+getClass().getName());
		map.put(e,t);
		t.start();
	}
	
	
	private boolean isRunning(E e)
	{
		if(!map.containsKey(e)) return false;
		Thread t = (Thread) map.get(e);
		return t.isAlive();
	}
	
	
	private void perform(E e)
	{
		try{e.e();}
		catch(Exception e1)
		{Outside.err(this,"perform(E)",e1);}
	}
	
	
	private class Runnable1 implements Runnable
	{
		private E e;
		public Runnable1(E e) {this.e = e;}
		public void run() {perform(e);}
	}
}
