package a.entity.gus06.appli.gusexplorer.menu.config;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20160419";}


	private Service configManager;
	private Service performLoad;
	private Service repaintButton;
	private Service actionAdd;
	private Service actionRemove;
	private Service actionReplace;
	private Service actionRename;
	private Service actionManage;

	
	private JMenu1 menu;
	private Map map;
	

	public EntityImpl() throws Exception
	{
		configManager = Outside.service(this,"gus06.appli.gusexplorer.config.manager");
		performLoad = Outside.service(this,"gus06.appli.gusexplorer.config.perform.load");
		repaintButton = Outside.service(this,"gus06.swing.button.cust2.display");
		actionAdd = Outside.service(this,"gus06.appli.gusexplorer.action.config.add");
		actionRemove = Outside.service(this,"gus06.appli.gusexplorer.action.config.remove");
		actionReplace = Outside.service(this,"gus06.appli.gusexplorer.action.config.replace");
		actionRename = Outside.service(this,"gus06.appli.gusexplorer.action.config.rename");
		actionManage = Outside.service(this,"gus06.appli.gusexplorer.action.config.manage");
		
		menu = new JMenu1("Configs");
		map = new HashMap();
		
		updateMenu();
		configManager.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{updateMenu();}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	
	private void updateMenu()
	{
		try
		{
			menu.removeAll();
			
			add(actionReplace);
			add(actionRename);
			add(actionRemove);
			add(actionAdd);
			add(actionManage);
			
			menu.addSeparator();
		
			List names = (List) configManager.g();
			for(int i=0;i<names.size();i++)
			{
				String name = (String) names.get(i);
				JMenuItem1 item = findItem(name);
				JMenu parent = buildParent(name);
				parent.add(item);
			}
			
			menu.updateMenu();
		}
		catch(Exception e)
		{Outside.err(this,"updateMenu()",e);}
	}
	
	
	
	
	private JMenuItem1 findItem(String name) throws Exception
	{
		if(!map.containsKey(name)) map.put(name,new JMenuItem1(name));
		return (JMenuItem1) map.get(name);
	}
	
	private JMenu findParent(String name)
	{
		if(!map.containsKey(name)) map.put(name,new JMenu(name));
		return (JMenu) map.get(name);
	}
	
	
	
	
	private JMenu buildParent(String name)
	{
		if(!name.contains("_")) return menu;
		
		String parentName = name.substring(0,name.lastIndexOf("_"));
		
		JMenu parent = findParent(parentName);
		JMenu parent2 = buildParent(parentName);
		
		parent2.add(parent);
		return parent;
	}
	
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
	
	private void changeConfig(String name)
	{
		try{performLoad.p(name);}
		catch(Exception e)
		{Outside.err(this,"changeConfig(String)",e);}
	}
	
	
	private class JMenuItem1 extends JMenuItem implements ActionListener
	{
		private String name;
		public JMenuItem1(String name) throws Exception
		{
			super();
			this.name = name;
			repaintButton.v("CONFIG#"+name,this);
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{changeConfig(name);}
	}
	
	
	
	private class JMenu1 extends JMenu 
	{
		public JMenu1(String title)
		{
			super(title);
		}
		
		public void updateMenu()
		{
			synchronized(getTreeLock())
			{validateTree();}

			if(isDisplayable())
			{
				validate();
				repaint();
			}
		}
	}
}