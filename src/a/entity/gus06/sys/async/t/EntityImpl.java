package a.entity.gus06.sys.async.t;

import a.framework.*;
import java.util.Vector;

public class EntityImpl implements Entity, T, Runnable {

	public String creationDate() {return "20150214";}



	private Vector list;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		list = new Vector();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Holder holder = new Holder((T)o[0],o[1]);
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
		private T t = null;
		private Object input = null;
		private Object output = null;
		
		public Holder(T t, Object input)
		{
			this.t = t;
			this.input = input;
		}
		
		public Object g() throws Exception
		{return output;}
		
		public void initData() throws Exception
		{
			output = build(t,input);
			startHolder(this);
		}
		
		public void run()
		{available();}
		
		private void available()
		{send(this,"available");}
	}
	
	
	
	private void startHolder(Runnable r)
	{new Thread(r,"THREAD_"+getClass().getName()+"_p").start();}
	
	
	
	private Object build(T t, Object input)
	{
		try{return t.t(input);}
		catch(Exception e)
		{
			Outside.err(this,"build(T,Object)",e);
			return e;
		}
	}
}
