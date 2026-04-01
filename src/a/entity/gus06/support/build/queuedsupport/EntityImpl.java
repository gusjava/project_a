package a.entity.gus06.support.build.queuedsupport;

import a.framework.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190712";}



	public Object t(Object obj) throws Exception
	{
		ArrayBlockingQueue queue = toQueue(obj);
		return new QueuedSupport(queue);
	}
	
	
	
	private ArrayBlockingQueue toQueue(Object obj) throws Exception
	{
		if(obj instanceof ArrayBlockingQueue) return (ArrayBlockingQueue) obj;
		if(obj instanceof S && obj instanceof G) return new ArrayBlockingQueue1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}



	
	private class QueuedSupport extends S1 implements Runnable, G, R, E
	{
		private ArrayBlockingQueue queue;
		private Object data;
		private Thread t;
		
		public QueuedSupport(ArrayBlockingQueue queue)
		{
			this.queue = queue;
			t = new Thread(this,"THREAD_"+EntityImpl.class.getName());
			t.start();
		}
		
		
		public Object g() throws Exception
		{return data;}
		
		
		public void e() throws Exception
		{
			t.interrupt();
			listeners().clear();
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("queue")) return queue;
			if(key.equals("thread")) return t;
			if(key.equals("keys")) return new String[]{"queue","thread"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		public void run()
		{while(perform()){}}
	
	
		private boolean perform()
		{
			try
			{
				data = queue.take();
				received();
				return true;
			}
			catch(InterruptedException e)
			{return false;}
		}
		
		private void received()
		{send(this,"received()");}
	}
	
	
	
	
	
	private class ArrayBlockingQueue1 extends ArrayBlockingQueue implements ActionListener, P
	{
		private G g;
		
		public ArrayBlockingQueue1(Object obj) throws Exception
		{
			super(100);
			g = (G) obj;
			((S) obj).addActionListener(this);
		}
		
		public void p(Object obj) throws Exception
		{
			put(obj);
		}
		
		public void actionPerformed(ActionEvent e)
		{transfert(g,this);}
	}
	
	
	
	private void transfert(G g, P p)
	{
		try{p.p(g.g());}
		catch(Exception e)
		{Outside.err(this,"transfert(G,P)",e);}
	}
}
