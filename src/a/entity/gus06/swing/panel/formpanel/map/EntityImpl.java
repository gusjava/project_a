package a.entity.gus06.swing.panel.formpanel.map;

import a.framework.*;
import java.util.Map;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, I, E, P, G {

	public String creationDate() {return "20140804";}


	private Service panel;
	private Service custLabel;
	private Map map;
	
	public EntityImpl() throws Exception
	{
		panel = Outside.service(this,"*gus06.swing.panel.formpanel");
		custLabel = Outside.service(this,"gus06.swing.label.cust.popup.menu1");
	}
	
	
	public Object i() throws Exception
	{return panel.i();}
	
	
	public Object g() throws Exception
	{return map;}
	
	
	
	public void e() throws Exception
	{reset();}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		panel.e();
		if(map==null || map.isEmpty()) return;
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			String value = ""+map.get(key);
			
			JLabel label = new JLabel(value);
			custLabel.p(label);
			panel.v(key,label);
		}
	}
	
	
	private void reset() throws Exception
	{
		map = null;
		panel.e();
	}

}
