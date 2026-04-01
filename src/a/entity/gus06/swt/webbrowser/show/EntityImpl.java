package a.entity.gus06.swt.webbrowser.show;

import a.framework.*;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, E, P, R, G, Runnable {

	public String creationDate() {return "20190708";}


	private Service queuedSupport;

	private WebBrowser webBrowser;
	private String location;
	private Thread t;
	
	private G g;
	private ActionListener listener;
	
	
	public EntityImpl() throws Exception
	{
		queuedSupport = Outside.service(this,"gus06.support.build.queuedsupport");
		
		listener = new ActionListener() {
			public void actionPerformed(ActionEvent e){newLocation();}
		};
	}
	
	
	public Object g() throws Exception
	{return g!=null ? g.g() : null;}
	
	
	
	public void e() throws Exception
	{initBrowser();}
	
	
	
	public void p(Object obj) throws Exception
	{
		location = toLocation(obj);
		if(webBrowser!=null)
		{
			webBrowser.setLocation(location);
			return;
		}
		initBrowser();
	}
	
	
	
	private void initBrowser() throws Exception
	{
		if(t==null || !t.isAlive())
		{
			t = new Thread(this,"THREAD_"+getClass().getName());
			t.start();
		}
		t.join();
	}
	
	
	public void run()
	{
		try
		{
			webBrowser = new WebBrowser();
		
			ArrayBlockingQueue queue = webBrowser.getQueue();
			
			if(g!=null) ((S)g).removeActionListener(listener);
			g = (G) queuedSupport.t(queue);
			((S)g).addActionListener(listener);
			
			webBrowser.open(location);  //BLOCKING UNTIL BROWSER CLOSED
			webBrowser = null;
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	public Object r(String key) throws Exception
	{
		return null;
	}
	
	
	
	public String toLocation(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof URL) return ((URL) obj).toString();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private void newLocation()
	{send(this,"newLocation()");}
}
