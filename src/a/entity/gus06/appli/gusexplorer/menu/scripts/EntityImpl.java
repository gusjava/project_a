package a.entity.gus06.appli.gusexplorer.menu.scripts;

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
import javax.swing.SwingUtilities;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20160909";}


	private Service manager;
	private Service repaintButton;
	
	
	private JMenu1 menu;
	private Map map1;
	private Map map2;
	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.scripts.tools.manager");
		repaintButton = Outside.service(this,"gus06.swing.button.cust2.display");
		
		menu = new JMenu1("Scripts");
		map1 = new HashMap();
		map2 = new HashMap();
		
		updateMenu();
		manager.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{updateMenu();}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	
	private void updateMenu()
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {updateMenu_();}
		});
	}
	
	private void updateMenu_()
	{
		try
		{
			menu.removeAll();
			map1.clear();
			map2.clear();
			
			List names = (List) manager.g();
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
		{Outside.err(this,"updateMenu_()",e);}
	}
	
	
	
	
	private JMenuItem1 findItem(String name) throws Exception
	{
		if(!map1.containsKey(name)) map1.put(name,new JMenuItem1(name));
		return (JMenuItem1) map1.get(name);
	}
	
	private JMenu findParent(String name)
	{
		if(!map2.containsKey(name)) map2.put(name,new JMenu1(name));
		return (JMenu) map2.get(name);
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
	
	
	
	
	
	private void executeScript(String name)
	{
		try{manager.p(name);}
		catch(Exception e)
		{Outside.err(this,"executeScript(String)",e);}
	}
	
	
	private class JMenuItem1 extends JMenuItem implements ActionListener, Runnable
	{
		private String name;
		public JMenuItem1(String name) throws Exception
		{
			super();
			this.name = name;
			repaintButton.v("FILE_gus#"+lastPart(name),this);
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{new Thread(this,"THREAD_"+getClass().getName()).start();}
		
		public void run()
		{executeScript(name);}
	}
	
	
	
	private class JMenu1 extends JMenu 
	{
		public JMenu1(String name)
		{super(lastPart(name));}
		
		public void updateMenu()
		{
			synchronized(getTreeLock())
			{validateTree();}

			if(isDisplayable())
			{validate();repaint();}
		}
	}
	
	
	private String lastPart(String name)
	{
		String[] n = name.split("_");
		return n.length>0 ? n[n.length-1] : name;
	}
}