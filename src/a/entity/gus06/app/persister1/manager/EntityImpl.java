package a.entity.gus06.app.persister1.manager;

import a.framework.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, V, R, ActionListener {

	public String creationDate() {return "20140912";}


	public static final long LAPSE = 1500;
	
	private Service persister1;
	private Service exit;

	private Timer timer;
	private TimerTask task;
	private Map map;
	
	

	public EntityImpl() throws Exception
	{
		persister1 = Outside.service(this,"gus06.app.persister1");
		exit = Outside.service(this,"gus06.app.execute.exit");
		
		map = new HashMap();
		
		task = new TimerTask() {public void run() {save();}};
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
		
		exit.addActionListener(this);
	}
	
	
	
	public Object r(String key) throws Exception
	{return persister1.r(key);}
	
	
	public void v(String key, Object obj) throws Exception
	{add(key,(G) obj);}


	public void actionPerformed(ActionEvent e)
	{save();}
	
	
	private synchronized void add(String key, G g) throws Exception
	{
		map.put(key,g);
		persister1.v(key,g.g());
	}
	
	
	
	private synchronized void save()
	{
		try
		{
			Iterator it = map.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				G g = (G) map.get(key);
				persister1.v(key,g.g());
			}
		}
		catch(Exception e)
		{Outside.err(this,"save()",e);}
	}
}
