package a.entity.gus06.swing.panel.formpanel.panel1;

import a.framework.*;
import java.util.Map;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, I, V, E, P, R {

	public String creationDate() {return "20191215";}


	private Service panel;
	private Service custLabel;
	
	public EntityImpl() throws Exception
	{
		panel = Outside.service(this,"*gus06.swing.panel.formpanel");
		custLabel = Outside.service(this,"gus06.swing.label.cust.popup.menu1");
	}
	
	
	
	public Object i() throws Exception
	{return panel.i();}
	
	
	public void e() throws Exception
	{panel.e();}
	
	
	public void v(String key, Object obj) throws Exception
	{handle(key,""+obj);}
	
	
	public Object r(String key) throws Exception
	{return handle(key,"");}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof String) 
		{
			if(obj.equals("sep")) addSeparator();
			return;
		}
		if(obj instanceof Map)
		{
			addMap((Map) obj);
			return;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void addMap(Map map) throws Exception
	{
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			String value = ""+map.get(key);
			handle(key,value);
		}
	}
	
	
	private void addSeparator() throws Exception
	{handle(" "," ");}
	
	
	
	private JLabel handle(String key, String value) throws Exception
	{
		JLabel label = new JLabel(value);
		custLabel.p(label);
		panel.v(key,label);
		return label;
	}
}
