package a.entity.gus06.appli.gusclient1.menu.space.cust;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JMenu;


public class EntityImpl implements Entity, V, ActionListener {

	public String creationDate() {return "20140812";}


	
	private Service spaceManager;
	private Map menus;
	
	public EntityImpl() throws Exception
	{
		spaceManager = Outside.service(this,"gus06.appli.gusclient1.space.manager");
		menus = new HashMap();
		spaceManager.addActionListener(this);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JMenu menu = (JMenu) obj;
		menus.put(key,menu);
		boolean isSelected = spaceManager.g().equals(key);
		menu.setVisible(isSelected);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{updateAll();}
	
	
	
	private void updateAll()
	{
		try
		{
			String selected = (String) spaceManager.g();
			Iterator it = menus.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				JMenu menu = (JMenu) menus.get(key);
				menu.setVisible(key.equals(selected));
			}
		}
		catch(Exception e)
		{Outside.err(this,"updateAll()",e);}
	}
}
