package a.entity.gus06.app.init.maincontrols;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class EntityImpl implements Entity, ActionListener {

	public String creationDate() {return "20140919";}


	private Service unique;
	private Service subMap;
	private Service eventSupport;
	private Service codeToDisplay;
	private Service executeToRunnable;

	private Map threads;

	public EntityImpl() throws Exception
	{
		unique = Outside.service(this,"entityunique");
		subMap = Outside.service(this,"gus06.app.prop.submap.control");
		eventSupport = Outside.service(this,"gus06.awt.keyevent.support");
		codeToDisplay = Outside.service(this,"gus06.tostring.keycode");
		executeToRunnable = Outside.service(this,"gus.y.convert1.executetorunnable");
		
		threads = new HashMap();
		eventSupport.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	private void perform()
	{
		try
		{
			Map map = (Map) subMap.g();
			if(map.isEmpty()) return;
			
			String code = (String) eventSupport.g();
			String display = (String) codeToDisplay.t(code);
			
			if(display==null) return;
			if(!map.containsKey(display)) return;
			if(hasAliveThread(display)) return;
			
			
			String entity = (String) map.get(display);
			Runnable r = build(entity);
			
			Thread t = new Thread(r,"THREAD_"+getClass().getName()+"_"+display);
			
			threads.put(display,t);
			t.start();
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}


	
	private Runnable build(String entity) throws Exception
	{
		E ex = (E) unique.t(entity);
		return (Runnable) executeToRunnable.t(ex);
	}
	
	
	private boolean hasAliveThread(String key)
	{
		if(!threads.containsKey(key)) return false;
		Thread t = (Thread) threads.get(key);
		return t.isAlive();
	}
}
