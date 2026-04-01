package a.entity.gus06.sys.webserver1.main.engine.receiver;

import a.framework.*;
import java.util.*;

public class EntityImpl extends S1 implements Entity, P, E, G, Runnable {

	public String creationDate() {return "20140928";}


	private Service formatInput;

	private Thread t;
	private Map received;
	
	private Map input;
	
	
	
	public EntityImpl() throws Exception
	{
		formatInput = Outside.service(this,"gus06.sys.webserver1.format.input");
		received = new HashMap();
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		synchronized(received)
		{enqueueByteArray(received,o[0],o[1]);}
	}
	
	
	
	public Object g() throws Exception
	{return input;}
	
	
	
	
	public void e() throws Exception
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void run()
	{
		while(true) {perform();sleep1();}
	}
	
	
	private void sleep1()
	{
		try{Thread.sleep(1);}
		catch(Exception e){Outside.err(this,"sleep1()",e);}
	}
	
	
	
	
	private void perform()
	{
		Object data = null;
		synchronized(received)
		{data = retrieveData(received);}
		if(data==null) return;
		
		input = formatInput(data);
		if(input!=null) dataIsReady();
	}
	
	
	
	private void dataIsReady()
	{send(this,"dataIsReady()");}




	private Map formatInput(Object data)
	{
		try{return (Map) formatInput.t(data);}
		catch(Exception e)
		{Outside.err(this,"formatInput(Object)",e);}
		return null;
	}





	private Object retrieveData(Map map)
	{
		if(map.isEmpty()) return null;
		Iterator it = map.keySet().iterator();
		Object key = it.next();
		List queue = (List) map.get(key);
		
		if(queue.isEmpty())
		{it.remove();return null;}
		
		Object value = queue.get(0);
		queue.remove(0);
		if(queue.isEmpty()) it.remove();
		
		return new Object[]{key,value};
	}
	
	
	
	
	private void enqueueByteArray(Map map, Object key, Object data)
	{
		if(!map.containsKey(key)) map.put(key,new ArrayList());
		List queue = (List) map.get(key);
		queue.add(data);
	}
}
