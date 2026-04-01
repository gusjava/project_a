package a.entity.gus06.appli.gusclient1.menu.spaces;


import a.framework.*;

import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140718";}

	public static final String KEY1 = "gusclient1_menu_spaces";
	public static final String KEY2 = "gusclient1_space_";


	
	private Service custMenu;
	
	private Service spaceManager;
	private Service spaceList;
	private Service localize;
	
	private JMenu menu;
	

	public EntityImpl() throws Exception
	{
		custMenu = Outside.service(this,"gus06.appli.gusclient1.menu.spaces.cust");
		
		spaceManager = Outside.service(this,"gus06.appli.gusclient1.space.manager");
		spaceList = Outside.service(this,"gus06.appli.gusclient1.space.list");
		localize = Outside.service(this,"gus06.ling.localize.manager");
		
		List l = (List) spaceList.g();
		String id0 = (String) spaceManager.g();
		
		menu = new JMenu("Spaces");
		custMenu.p(menu);
		
		localize.v(KEY1,menu);
		
		ButtonGroup group = new ButtonGroup();
		
		for(int i=0;i<l.size();i++)
		{
			String id = (String) l.get(i);
			boolean selected = id.equals(id0);
			
			JRadioButtonMenuItem1 item = new JRadioButtonMenuItem1(id,selected);
			localize.v(KEY2+id,item);
			
			group.add(item);
			menu.add(item);
		}
	}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	
	private void changeSpace(String id)
	{
		try{spaceManager.p(id);}
		catch(Exception e)
		{Outside.err(this,"changeSpace(String)",e);}
	}
	
	
	private void updateItem(JRadioButtonMenuItem item, String id)
	{
		try
		{
			if(spaceManager.g().equals(id))
			item.setSelected(true);
		}
		catch(Exception e)
		{Outside.err(this,"updateItem(JRadioButtonMenuItem,String)",e);}
	}
	
	
	
	
	
	private class JRadioButtonMenuItem1 extends JRadioButtonMenuItem implements ChangeListener, ActionListener
	{
		private String id;
		public JRadioButtonMenuItem1(String id, boolean selected) throws Exception
		{
			super(id,selected);
			this.id = id;
		
			addChangeListener(this);
			spaceManager.addActionListener(this);
		}
		
		public void stateChanged(ChangeEvent e)
		{if(isSelected()) changeSpace(id);}
		
		public void actionPerformed(ActionEvent e)
		{updateItem(this,id);}
	}
}
