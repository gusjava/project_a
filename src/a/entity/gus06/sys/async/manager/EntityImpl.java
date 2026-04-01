package a.entity.gus06.sys.async.manager;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, R, T, Runnable {

	public String creationDate() {return "20141028";}


	private Service newEntity;
	private Vector list;
	
	private Thread t;
	
	
	public EntityImpl() throws Exception
	{
		newEntity = Outside.service(this,"entitynew");
		list = new Vector();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	public Object r(String key) throws Exception
	{return findHolder(key);}
	
	
	
	
	private Object findHolder(String name) throws Exception
	{
		if(name==null || name.equals("")) throw new Exception("Invalid name: "+name);
		
		Holder holder = new Holder(name);
		list.add(holder);
		initThread();
		return holder;
	}
	
	
	private void initThread()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void run()
	{
		while(!list.isEmpty())
		{perform();sleep1();}
	}
	
	
	
	private void perform()
	{
		try
		{
			Holder holder = (Holder) list.remove(0);
			holder.initData();
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	
	
	
	private void sleep1()
	{
		try{Thread.sleep(1);}
		catch(Exception e){Outside.err(this,"sleep1()",e);}
	}
	
	
	
	
	private class Holder extends S1 implements G, Runnable
	{
		private String name = null;
		private Object data = null;
		
		public Holder(String name)
		{this.name = name;}
		
		public Object g() throws Exception
		{return data;}
		
		public void initData() throws Exception
		{
			data = build(name);
			new Thread(this,"THREAD_"+EntityImpl.class.getName()).start();
		}
		
		public void run()
		{available();}
		
		private void available()
		{send(this,"available");}
	}
	
	
	
	
	private Object build(String entityName)
	{
		try{return newEntity.t(entityName);}
		catch(Exception e)
		{
			Outside.err(this,"build(String)",e);
			return e;
		}
	}
}
