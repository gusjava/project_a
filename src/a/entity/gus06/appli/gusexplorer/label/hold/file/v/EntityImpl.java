package a.entity.gus06.appli.gusexplorer.label.hold.file.v;

import java.io.File;
import a.framework.*;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import java.util.ArrayList;
import java.awt.Color;

public class EntityImpl extends S1 implements Entity, I, P, G, V, R {

	public String creationDate() {return "20200403";}
	
	public static final int HISTORY_LIMIT = 15;

	private Service dnd;
	private Service popupMenuBuilder;
	private Service getIcon;
	private Service getColor;
	private Service toClipboard;
	private Service focusOnClick;
	private Service fullPanel;
	private Service onKey;
	private Service open;
	private Service showInFrame;
	private Service runTask1;
	private Service runTask2;
	private Service runPreviousTask;
	private Service copyContent;
	private Service copyPath;
	private Service copyName;
	private Service pasteContent;
	private Service displayInfos1;
	private Service displayInfos2;
	private Service buildMenuItem;
	private Service rename;
	private Service duplicate;
	private Service delete;
	
	private JLabel label;
	private JPopupMenu popupMenu;
	
	private File file;
	private List history;
	
	private JMenu menuHistory;
	private JMenu menuCopy;
	private JMenu menuPaste;
	private JMenu menuPerform;
	private JMenu menuInfos;
	private JMenu menuShow;




	public EntityImpl() throws Exception
	{
		dnd = Outside.service(this,"gus06.awt.dnd");
		popupMenuBuilder = Outside.service(this,"gus06.swing.popupmenu.builder1");
		getIcon = Outside.service(this,"gus06.file.icon.t1");
		getColor = Outside.service(this,"gus06.file.findcolor1");
		toClipboard = Outside.service(this,"gus06.clipboard.access.file");
		focusOnClick = Outside.service(this,"gus06.swing.comp.cust.focusonclicked");
		fullPanel = Outside.service(this,"gus06.appli.gusexplorer.handle.fullpanel");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		open = Outside.service(this,"gus06.file.execute.generic");
		showInFrame = Outside.service(this,"gus06.file.editor.show.inframe");
		runTask1 = Outside.service(this,"gus06.sys.runtask1.input.path");
		runTask2 = Outside.service(this,"gus06.sys.runtask2.input.path");
		runPreviousTask = Outside.service(this,"gus06.sys.runtask1.input.path.previous");
		copyContent = Outside.service(this,"gus06.sys.clipboard1.p.listfiles.contents");
		copyPath = Outside.service(this,"gus06.sys.clipboard1.p.listfiles.paths");
		copyName = Outside.service(this,"gus06.sys.clipboard1.p.listfiles.names");
		pasteContent = Outside.service(this,"gus06.sys.clipboard1.writecontent");
		displayInfos1 = Outside.service(this,"gus06.dirfile.perform.display.infos1");
		displayInfos2 = Outside.service(this,"gus06.dirfile.perform.display.infos2");
		buildMenuItem = Outside.service(this,"gus06.swing.menuitem.builder1");
		rename = Outside.service(this,"gus06.dirfile.perform.rename.ask");
		duplicate = Outside.service(this,"gus06.dirfile.perform.duplicate.ask");
		delete = Outside.service(this,"gus06.dirfile.perform.remove.ask");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		popupMenu = (JPopupMenu) popupMenuBuilder.t(label);
		
		focusOnClick.p(label);
		fullPanel.p(label);
		dnd.p(new Object[]{label,(P) this::receive,this});
		
		history = new ArrayList();
		
		menuHistory = new JMenu("Recent (0)");
		menuCopy = new JMenu("Copy");
		menuPaste = new JMenu("Paste");
		menuPerform = new JMenu("Perform");
		menuInfos = new JMenu("Infos");
		menuShow = new JMenu("Show/Open");
		
		addMenu(menuCopy,"Copy", "ctrl c", (E) this::copyFile);
		addMenu(menuCopy,"Copy content", "ctrl alt c", (E) this::copyContent);
		addMenu(menuCopy,"Copy path", "ctrl shift c", (E) this::copyPath);
		addMenu(menuCopy,"Copy name", "shift alt c", (E) this::copyName);
		
		addMenu(menuPaste,"Paste", "ctrl v", (E) this::pasteFile);
		addMenu(menuPaste,"Paste content", "ctrl alt v", (E) this::pasteContent);
		
		addMenu(menuPerform,"Rename", "F2", (E) this::rename);
		addMenu(menuPerform,"Duplicate", "F3", (E) this::duplicate);
		addMenu(menuPerform,"Delete", "DEL", (E) this::delete);
		
		addMenu(menuInfos,"Display infos 1", "F4", (E) this::displayInfos1);
		addMenu(menuInfos,"Display infos 2", "ctrl F4", (E) this::displayInfos2);
		
		addMenu(menuShow,"Open", "space", (E) this::open);
		addMenu(menuShow,"Show in frame", "ctrl shift space", (E) this::showInFrame);
		addMenu(menuShow,"Load parent", "ctrl space", (E) this::loadParent);
		
		popupMenu.add(menuHistory);
		popupMenu.add(menuCopy);
		popupMenu.add(menuPaste);
		popupMenu.add(menuPerform);
		popupMenu.add(menuInfos);
		popupMenu.add(menuShow);
		
		popupMenu.addSeparator();
		
		addMenu("Run task", "F7", (E) this::runTask1);
		addMenu("Run custom task", "shift F7", (E) this::runTask2);
		addMenu("Run previous task", "alt F7", (E) this::runPreviousTask);
	}
	
	
	
	
	private void addMenu(JMenu menu, String title, String key, E exe) throws Exception
	{
		menu.add(buildItem(title, key, exe));
	}
	
	private void addMenu(String title, String key, E exe) throws Exception
	{
		popupMenu.add(buildItem(title, key, exe));
	}
	
	private JMenuItem buildItem(String title, String key, E exe) throws Exception
	{
		String display = title+" ("+key+")";
		onKey.p(new Object[]{label, key, exe});
		return (JMenuItem) buildMenuItem.t(new Object[]{exe, display});
	}
	
	
	
	
	public Object g() throws Exception
	{return file;}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		refresh();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("history")) {setHistory((List) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("history")) return history;
		if(key.equals("keys")) return new String[]{"history"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void refresh() throws Exception
	{
		if(file==null)
		{
			label.setIcon(null);
			label.setToolTipText(null);
			label.setText(" ");
		}
		else
		{
			label.setIcon(icon(file));
			label.setText(file.getName());
			label.setToolTipText(file.getAbsolutePath());
			
			addFileToHistory();
		}
	}

	
	
	private Icon icon(File file) throws Exception
	{return (Icon) getIcon.t(file);}
	
	private Color color(File file) throws Exception
	{return (Color) getColor.t(file);}
	
	
	
	private void open()
	{
		try{if(file!=null) open.p(file);}
		catch(Exception e){Outside.err(this,"open()",e);}
	}
	
	private void showInFrame()
	{
		try{if(file!=null) showInFrame.p(file);}
		catch(Exception e){Outside.err(this,"showInFrame()",e);}
	}
	
	private void loadParent()
	{
		try{if(file!=null) reloadFile(file.getParentFile());}
		catch(Exception e){Outside.err(this,"loadParent()",e);}
	}
	
	private void runTask1()
	{
		try{if(file!=null) runTask1.p(file);}
		catch(Exception e){Outside.err(this,"runTask1()",e);}
	}
	
	private void runTask2()
	{
		try{if(file!=null) runTask2.p(file);}
		catch(Exception e){Outside.err(this,"runTask2()",e);}
	}
	
	private void runPreviousTask()
	{
		try{if(file!=null) runPreviousTask.p(file);}
		catch(Exception e){Outside.err(this,"runPreviousTask()",e);}
	}
	
	private void copyContent()
	{
		try{if(file!=null) copyContent.p(file);}
		catch(Exception e){Outside.err(this,"copyContent()",e);}
	}
	
	private void copyFile()
	{
		try{if(file!=null) toClipboard.p(file);}
		catch(Exception e){Outside.err(this,"copyFile()",e);}
	}
	
	private void copyPath()
	{
		try{if(file!=null) copyPath.p(file);}
		catch(Exception e){Outside.err(this,"copyPath()",e);}
	}
	
	private void copyName()
	{
		try{if(file!=null) copyName.p(file);}
		catch(Exception e){Outside.err(this,"copyName()",e);}
	}
	
	private void displayInfos1()
	{
		try{if(file!=null) displayInfos1.p(file);}
		catch(Exception e){Outside.err(this,"displayInfos1()",e);}
	}
	
	private void displayInfos2()
	{
		try{if(file!=null) displayInfos2.p(file);}
		catch(Exception e){Outside.err(this,"displayInfos2()",e);}
	}
	
	private void rename()
	{
		try{
			if(file==null) return;
			File f = (File) rename.t(file);
			if(f!=null) receive(f);
		}
		catch(Exception e)
		{Outside.err(this,"rename()",e);}
	}
	
	private void duplicate()
	{
		try{if(file!=null) duplicate.p(file);}
		catch(Exception e){Outside.err(this,"duplicate()",e);}
	}
	
	private void delete()
	{
		try
		{
			if(file==null) return;
			boolean deleted = delete.f(file);
			if(deleted) receive(null);
		}
		catch(Exception e){Outside.err(this,"delete()",e);}
	}
	
	private void pasteFile()
	{
		try
		{
			File f = (File) toClipboard.g();
			if(f!=null) receive(f);
		}
		catch(Exception e){Outside.err(this,"pasteFile()",e);}
	}
	
	private void pasteContent()
	{
		try
		{
			if(file==null) return;
			pasteContent.p(file);
			contentChanged();
		}
		catch(Exception e){Outside.err(this,"pasteContent()",e);}
	}
	
	
	
	private void setHistory(List history) throws Exception
	{
		this.history = history;
		rebuildHistoryMenu();
	}
	
	private void addFileToHistory() throws Exception
	{
		if(file==null) return;
		String path = file.getAbsolutePath();
		if(history.contains(path)) history.remove(path);
		history.add(0,path);
		
		while(history.size()>HISTORY_LIMIT)
		history.remove(history.size()-1);
		
		rebuildHistoryMenu();
		historyChanged();
	}
	
	
	private void rebuildHistoryMenu() throws Exception
	{
		menuHistory.setLabel("Recent ("+history.size()+")");
		menuHistory.removeAll();
		for(int i=1;i<history.size();i++)
		menuHistory.add(new JMenuItemHistory((String) history.get(i)));
	}
	
	
	private class JMenuItemHistory extends JMenuItem implements ActionListener
	{
		private File file;
		
		public JMenuItemHistory(String path) throws Exception
		{
			super(path);
			file = new File(path);
			
			setIcon(icon(file));
			setForeground(color(file));
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{reloadFile(file);}
	}
	
	private void reloadFile(File file)
	{
		try{receive(file);}
		catch(Exception e)
		{Outside.err(this,"reloadFile(File)",e);}
	}
	
	
	
	
	
	private void receive(Object obj) throws Exception
	{
		file = (File) obj;
		refresh();
		received();
	}
	
	
	
	
	private void received()
	{send(this,"received()");}
	
	private void contentChanged()
	{send(this,"contentChanged()");}
	
	private void historyChanged()
	{send(this,"historyChanged()");}
}