package a.entity.gus06.appli.gusdbmanager.location.choosermenu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import a.framework.*;



public class EntityImpl implements Entity, I, ActionListener {



	public String creationDate() {return "20150613";}

	
	public static final String UNKNOWN = "?";

	
	private Service dataHolder;
	private Service locationHolder;
	
	private JMenu menu;


	public EntityImpl() throws Exception
	{
		dataHolder = Outside.service(this,"gus06.appli.gusdbmanager.data.holder");
		locationHolder = Outside.service(this,"gus06.appli.gusdbmanager.location.holder");
		
		
		menu = new JMenu("Location");
		
		dataHolder.addActionListener(this);
		locationHolder.addActionListener(this);
		updateMenu();
	}



	public Object i() throws Exception
	{return menu;}


	
	public void actionPerformed(ActionEvent e)
	{updateMenu();}
	
	
	
	private void updateMenu()
	{
		try
		{
			menu.removeAll();
			Map data = (Map) dataHolder.g();
			String location1 = (String) locationHolder.g();
			if(location1==null) location1 = UNKNOWN;
			
			List locations = findLocations(data);
			locations.add(UNKNOWN);
			
			ButtonGroup group = new ButtonGroup();
			
			for(int i=0;i<locations.size();i++)
			{
				String location = (String) locations.get(i);
				boolean selected = equals(location,location1);
				
				JRadioButtonMenuItem1 item = new JRadioButtonMenuItem1(location,selected);
				
				group.add(item);
				menu.add(item);
			}
		}
		catch(Exception e)
		{Outside.err(this,"updateMenu()",e);}
	}
	
	
	
	
	private void updateLocation(String location)
	{
		try
		{
			if(location.equals(UNKNOWN)) location = null;
			
			locationHolder.removeActionListener(this);
			locationHolder.p(location);
			locationHolder.addActionListener(this);
		}
		catch(Exception e)
		{Outside.err(this,"updateLocation(String)",e);}
	}
	
	
	
	
	
	
	
	private List findLocations(Map data)
	{
		List list = new ArrayList();
		Iterator it = data.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.startsWith("2-"))
			{
				String location = key.split("\\.")[0].substring(2);
				if(!list.contains(location)) list.add(location);
			}
		}
		Collections.sort(list);
		return list;
	}
	
	
	
	
	
	private boolean equals(String s1, String s2)
	{
		if(s1==null && s2==null) return true;
		if(s1==null || s2==null) return false;
		return s1.equals(s2);
	}
	
	
	
	
	
	private class JRadioButtonMenuItem1 extends JRadioButtonMenuItem implements ActionListener
	{
		private String location;
		
		public JRadioButtonMenuItem1(String location, boolean selected)
		{
			super(location,selected);
			addActionListener(this);
			this.location = location;
		}
		
		public void actionPerformed(ActionEvent e)
		{if(isSelected()) updateLocation(location);}
	}
}
