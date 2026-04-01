package a.entity.gus06.appli.gusclient1.space.controls;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.List;
import java.awt.event.KeyEvent;

public class EntityImpl implements Entity, ActionListener {

	public String creationDate() {return "20140730";}


	private Service eventSupport;
	private Service spaceManager;
	private Service spaceList;
	

	public EntityImpl() throws Exception
	{
		eventSupport = Outside.service(this,"gus06.awt.keyevent.support");
		spaceManager = Outside.service(this,"gus06.appli.gusclient1.space.manager");
		spaceList = Outside.service(this,"gus06.appli.gusclient1.space.list");
		
		eventSupport.addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			int move = getMove();
			if(move==0) return;
			
			List list = (List) spaceList.g();
			String previous = (String) spaceManager.g();
			
			if(list.isEmpty()) throw new Exception("Empty list for spaces");
			
			if(previous==null || list.indexOf(previous)==-1)
			{spaceManager.p(list.get(0));return;}
			
			int index = list.indexOf(previous)+move;
			if(index==-1) index = list.size()-1;
			else if(index==list.size()) index = 0;
			
			spaceManager.p(list.get(index));
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	
	
	private int getMove() throws Exception
	{
		String code = (String) eventSupport.g();
		if(code.equals(""+KeyEvent.VK_F11)) return -1;
		if(code.equals(""+KeyEvent.VK_F12)) return 1;
		return 0;
	}
}
