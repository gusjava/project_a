package a.entity.gus06.appli.gusclient1.alert.manager;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl extends S1 implements Entity, V, R {

	public String creationDate() {return "20140730";}


	private String name;
	private Object src;
	private String event;
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("name")) return name;
		if(key.equals("src")) return src;
		if(key.equals("event")) return event;
		
		if(key.equals("keys")) return new String[]{"name","src","event"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{new Holder(key,(S) obj);}
	
	
	private void triggerAlert(String name, Object src, String event)
	{
		this.name = name;
		this.src = src;
		this.event = event;
		alert();
	}
		
	private void alert()
	{send(this,"alert()");}
	
	
	
	private class Holder implements ActionListener
	{
		private String name;
		private S s;
	
		public Holder(String name, S s) throws Exception
		{
			this.name = name;
			this.s = s;
			s.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{triggerAlert(name,s,e.getActionCommand());}
	}
}
