package a.entity.gus06.jna.keyboard.buffer.trigger.execute;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, V {

	public String creationDate() {return "20160915";}


	private Service buffer;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		buffer = Outside.service(this,"gus06.jna.keyboard.buffer");
		map = new HashMap();
		
		buffer.addActionListener(this);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		E execute = (E) obj;
		map.put(key,execute);
	}


	public void actionPerformed(ActionEvent e)
	{check();}
	
	
	private void check()
	{
		try
		{
			String s = (String) buffer.g();
			if(map.containsKey(s))
			{
				E execute = (E) map.get(s);
				execute.e();
			}
		}
		catch(Exception e)
		{Outside.err(this,"check()",e);}
	}
}
