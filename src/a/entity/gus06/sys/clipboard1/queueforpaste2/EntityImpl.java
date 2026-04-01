package a.entity.gus06.sys.clipboard1.queueforpaste2;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, P {

	public String creationDate() {return "20200113";}
	

	private Service clipboard;
	private Service mouse;

	private List queue;
	

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.sys.clipboard1.put.delay.ms300");
		mouse = Outside.service(this,"gus06.jna.mouse.support");
		
		queue = new ArrayList();
		mouse.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("rightButtonPressed()")) 
		perform();
	}
	
	
	public void p(Object obj) throws Exception
	{init((List) obj);}
	
	
	
	private synchronized void init(List list) throws Exception
	{
		queue.clear();
		queue.addAll(list);
		Object next = queue.remove(0);
		clipboard.p(next);
	}
	
	private synchronized void perform()
	{
		try
		{
			if(queue.isEmpty()) return;
			
			Object next = queue.remove(0);
			clipboard.p(next);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}

}
