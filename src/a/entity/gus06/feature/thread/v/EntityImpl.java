package a.entity.gus06.feature.thread.v;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20151010";}


	private HashMap map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		V v = (V) o[0];
		String key = (String) o[1];
		Object data = o[2];
		
		return new Runnable1(v,key,data);
	}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		V v = (V) o[0];
		String key = (String) o[1];
		Object data = o[2];
		
		if(isRunning(v)) return;
		
		Runnable r = new Runnable1(v,key,data);
		Thread t = new Thread(r,"THREAD_"+getClass().getName());
		map.put(v,t);
		t.start();
	}



	
	
	private boolean isRunning(V v)
	{
		if(!map.containsKey(v)) return false;
		Thread t = (Thread) map.get(v);
		return t.isAlive();
	}
	
	
	private void perform(V v, String key, Object data)
	{
		try{v.v(key,data);}
		catch(Exception e)
		{Outside.err(this,"perform(V,String,Object)",e);}
	}
	
	
	private class Runnable1 implements Runnable
	{
		private V v;
		private String key;
		private Object data;
		
		public Runnable1(V v, String key, Object data)
		{
			this.v = v;
			this.key = key;
			this.data = data;
		}
		public void run() {perform(v,key,data);}
	}
}
