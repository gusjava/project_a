package a.entity.gus06.sys.filetool.ext.webserver1.holder;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20161118";}

	public static final String URL = "url";
	public static final String USER = "user";
	public static final String PWD = "pwd";
	

	
	private Map map;
	
	private Thread t;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive()) return;
		
		map = (Map) obj;
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void run()
	{
		try
		{
			String url = get0(URL);
			String user = get0(USER);
			String pwd = get0(PWD);
		
			if(url==null) return;
			if(user==null) return;
			if(pwd==null) return;
			
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	private String get0(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
