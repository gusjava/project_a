package a.entity.gus06.appli.gusexplorer.gui.tabbedpane;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JTabbedPane;
import java.util.Map;
import java.awt.Component;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class EntityImpl extends Base implements Entity, I, F, T, V {

	public String creationDate() {return "20141208";}

	private Service manager;
	private Service closeable;
	private Service buildLabel;
	private Service buildHolder;
	private Service selection;
	private Service persister;
	
	private JTabbedPane tabbedPane;
	private ActionListener closeableListener;
	private ActionListener managerListener;

	public EntityImpl() throws Exception
	{
		super();
		
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		closeable = Outside.service(this,"*gus06.swing.tabbedpane.build.closeable");
		buildLabel = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane.filetolabel");
		buildHolder = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane.filetoholder");
		selection = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane.selection");
		persister = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		
		
		closeableListener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				
				if(s.equals("tabRemoved()")) tabRemoved();
				else if(s.equals("tabsInverted()")) tabsInverted();
				else if(s.equals("tabSelected()")) tabSelected();
			}
		};
		managerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dataModified();
			}
		};

		
		tabbedPane = (JTabbedPane) closeable.i();
		tabbedPane.setFocusTraversalKeysEnabled(false);
		installKeyBindings();
		
		closeable.addActionListener(closeableListener);
		manager.addActionListener(managerListener);
		
		rebuildTabs();
		persister.v(getClass().getName()+"_tab",tabbedPane);
	}
	
	
	public Object i() throws Exception
	{return tabbedPane;}
	
	
	public boolean f(Object obj) throws Exception
	{return hasFile((File) obj);}
	
	
	public Object t(Object obj) throws Exception
	{return holderAt((File) obj);}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("select")) {select((File) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void select(File file) throws Exception
	{
		if(!hasFile(file)) return;
		
		Object holder = holderAt(file);
		Object comp = ((I)holder).i();
		toCloseable("selectTab",comp);
	}
	
	
	private void dataModified()
	{
		try
		{
			Map modif = (Map) manager.r("lastModification");
			String action = (String) modif.get("action");
		
			if(action.equals("modify"))		pathModified(modif);
			else if(action.equals("reload"))	pathReloaded(modif);
			else if(action.equals("exchange"))	pathExchanged(modif);
			else if(action.equals("add"))		pathAdded(modif);
			else if(action.equals("remove"))	pathRemoved(modif);
			else if(action.equals("keep"))		pathKept(modif);
			
			else rebuildTabs();
		}
		catch(Exception e)
		{Outside.err(this,"dataModified()",e);}
	}
	
	
	private void tabRemoved()
	{
		try
		{
			int index = toIndex(closeable.r("removedIndex"));
			File file = fileAt(index);
			
			remove(file);
			toManager("remove",file);
			updateSelection();
		}
		catch(Exception e)
		{Outside.err(this,"tabRemoved()",e);}
	}
	
	
	private void tabsInverted()
	{
		try
		{
			int index0 = toIndex(closeable.r("invertedIndex0"));
			int index1 = toIndex(closeable.r("invertedIndex1"));
			
			File file0 = fileAt(index0);
			File file1 = fileAt(index1);
			
			toManager("permute",new File[]{file0,file1});
			updateSelection();
		}
		catch(Exception e)
		{Outside.err(this,"tabsInverted()",e);}
	}
	
	
	private void tabSelected()
	{
		try
		{
			updateSelection();
		}
		catch(Exception e)
		{Outside.err(this,"tabSelected()",e);}
	}
	
	
	private void pathModified(Map modif) throws Exception
	{
		File file1 = (File) modif.get("file1");
		File file2 = (File) modif.get("file2");
		
		modify(file1,file2);
		updateSelection();
	}
	
	
	private void pathReloaded(Map modif) throws Exception
	{
		File file = (File) modif.get("file");
		reload(file);
	}
	
	
	private void pathExchanged(Map modif) throws Exception
	{
		File file1 = (File) modif.get("file1");
		File file2 = (File) modif.get("file2");
			
		exchange(file1,file2);
		updateSelection();
	}
	
	
	private void pathAdded(Map modif) throws Exception
	{
		if(modif.containsKey("file"))
		{
			File file = (File) modif.get("file");
			addFile(file);
			updateSelection();
		}
		else if(modif.containsKey("list"))
		{
			List list = (List) modif.get("list");
			for(int i=0;i<list.size();i++)
			addFile((File) list.get(i));
			updateSelection();
		}
	}
	
	
	private void pathRemoved(Map modif) throws Exception
	{
		if(modif.containsKey("file"))
		{
			File file = (File) modif.get("file");
			removeFile(file);
			updateSelection();
		}
		else if(modif.containsKey("list"))
		{
			List list = (List) modif.get("list");
			for(int i=0;i<list.size();i++)
			removeFile((File) list.get(i));
			updateSelection();
		}
	}
	
	
	private void pathKept(Map modif) throws Exception
	{
		if(modif.containsKey("file"))
		{
			int index = toIndex(modif.get("index"));
			File file = (File) modif.get("file");
		
			keep(file);
		
			Component comp = tabbedPane.getComponentAt(index);
			Component[] cc = tabbedPane.getComponents();
		
			for(Component c:cc) if(c!=comp)
			toCloseable("removeTab",c);
		}
		else if(modif.containsKey("list"))
		{
			throw new Exception("Operation not supported yet");
		}
	}
	
	
	private void rebuildTabs() throws Exception
	{
		toCloseable("removeAll",null);
		clear();
		
		List l = (List) manager.g();
		for(int i=0;i<l.size();i++)
		addFile((File) l.get(i));
		
		updateSelection();
	}
	
	
	private void updateSelection() throws Exception
	{
		int index = tabbedPane.getSelectedIndex();
		if(index==-1) {selection.p(null);return;}
		
		File file = fileAt(index);
		P holder = holderAt(file);
		selection.p(holder);
	}
	
	
	private void addFile(File file) throws Exception
	{
		P label = (P) buildLabel.t(file);
		P holder = (P) buildHolder.t(file);
		Object comp = ((I)holder).i();
		
		add(file,label,holder);
		
		toCloseable("addCTab",new Object[]{label,comp});
		toCloseable("selectTab",comp);
	}
	
	
	private void removeFile(File file) throws Exception
	{
		P holder = holderAt(file);
		Object comp = ((I)holder).i();
		
		toCloseable("removeTab",comp);
		remove(file);
	}
	
	
	private File fileAt(int index) throws Exception
	{
		List l = (List) manager.g();
		if(index<0 || l.size()<=index)
			throw new Exception("Index out of bounds ["+index+"] for retrieving file (size="+l.size()+")");
		return (File) l.get(index);
	}
	
	
	private void toManager(String key, Object obj) throws Exception
	{
		manager.removeActionListener(managerListener);
		manager.v(key,obj);
		manager.addActionListener(managerListener);
	}
	
	private void toCloseable(String key, Object obj) throws Exception
	{
		closeable.removeActionListener(closeableListener);
		closeable.v(key,obj);
		closeable.addActionListener(closeableListener);
	}
	
	private void installKeyBindings()
	{
		InputMap im = tabbedPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap am = tabbedPane.getActionMap();
		
		im.put(
			KeyStroke.getKeyStroke(KeyEvent.VK_TAB,InputEvent.CTRL_DOWN_MASK),
			"nextTab"
		);
		im.put(
			KeyStroke.getKeyStroke(KeyEvent.VK_TAB,InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK),
			"prevTab"
		);
		
		am.put("nextTab",new AbstractAction(){
			public void actionPerformed(ActionEvent e)
			{
				int count = tabbedPane.getTabCount();
				if(count<=1) return;
				
				int index = tabbedPane.getSelectedIndex();
				tabbedPane.setSelectedIndex((index+1)%count);
			}
		});
		
		am.put("prevTab",new AbstractAction(){
			public void actionPerformed(ActionEvent e)
			{
				int count = tabbedPane.getTabCount();
				if(count<=1) return;
				
				int index = tabbedPane.getSelectedIndex();
				tabbedPane.setSelectedIndex((index-1+count)%count);
			}
		});
	}

}
