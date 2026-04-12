package a.entity.gus06.appli.gusexplorer.label.hold.file;

import java.io.File;
import a.framework.*;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20200403";}

	private Service dnd;
	private Service popupMenuBuilder;
	private Service getIcon;
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
		toClipboard = Outside.service(this,"gus06.clipboard.access.file");
		focusOnClick = Outside.service(this,"gus06.swing.comp.cust.focusonclicked");
		fullPanel = Outside.service(this,"gus06.appli.gusexplorer.handle.fullpanel");
		open = Outside.service(this,"gus06.file.execute.generic");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
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
		dnd.p(new Object[]{label,null,this});
		
		menuCopy = new JMenu("Copy");
		menuPaste = new JMenu("Paste");
		menuPerform = new JMenu("Perform");
		menuInfos = new JMenu("Infos");
		menuShow = new JMenu("Show/Open");
		
		addMenu(menuCopy,"Copy", "ctrl c", (E) this::copyFile);
		addMenu(menuCopy,"Copy content", "ctrl alt c", (E) this::copyContent);
		addMenu(menuCopy,"Copy path", "ctrl shift c", (E) this::copyPath);
		addMenu(menuCopy,"Copy name", "shift alt c", (E) this::copyName);
		
		addMenu(menuPaste,"Paste content", "ctrl alt v", (E) this::pasteContent);
		
		addMenu(menuPerform,"Rename", "F2", (E) this::rename);
		addMenu(menuPerform,"Duplicate", "F3", (E) this::duplicate);
		addMenu(menuPerform,"Delete", "DEL", (E) this::delete);
		
		addMenu(menuShow,"Open", "space", (E) this::open);
		addMenu(menuShow,"Show in frame", "ctrl shift space", (E) this::showInFrame);
		
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
	
	
	private void addSeparator()
	{popupMenu.addSeparator();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
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
		}
	}

	
	
	private Icon icon(File file) throws Exception
	{return (Icon) getIcon.t(file);}
	
	
	
	private void open()
	{
		try{if(file!=null) open.p(file);}
		catch(Exception e){Outside.err(this,"open()",e);}
	}
	
	private void showInFrame()
	{
		try{if(file!=null) showInFrame.p(file);}
		catch(Exception e)
		{Outside.err(this,"showInFrame()",e);}
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
	
	private void copyFile()
	{
		try{if(file!=null) toClipboard.p(file);}
		catch(Exception e){Outside.err(this,"copyFile()",e);}
	}
	
	private void copyContent()
	{
		try{if(file!=null) copyContent.p(file);}
		catch(Exception e){Outside.err(this,"copyContent()",e);}
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
	
	private void pasteContent()
	{
		try{if(file!=null) pasteContent.p(file);}
		catch(Exception e){Outside.err(this,"pasteContent()",e);}
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
			file = (File) rename.t(file);
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
			if(deleted) file = null;
		}
		catch(Exception e){Outside.err(this,"delete()",e);}
	}
}