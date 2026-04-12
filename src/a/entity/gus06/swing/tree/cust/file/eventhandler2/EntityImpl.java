package a.entity.gus06.swing.tree.cust.file.eventhandler2;

import a.framework.*;
import javax.swing.JTree;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.JMenu;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220603";}


	private Service popupMenuBuilder;
	private Service buildMenuItem;
	
	private Service goToRight;
	private Service goToLeft;
	private Service addNewTab;
	private Service copyContent;
	private Service pasteContent;
	private Service cutContent;
	private Service copy;
	private Service search;
	private Service createDir;
	private Service renameTStamp;
	private Service duplicateTStamp;
	private Service displayInfos2;
	private Service copyPath;
	private Service copyName;
	private Service showInFrame;
	private Service pastePath;
	private Service cutPath;
	private Service changeRoot;
	private Service paste;
	private Service cut;
	private Service linkGus;
	private Service remove;
	private Service createFile;
	private Service renameAsk;
	private Service duplicateAsk;
	private Service displayInfos1;
	private Service refresh;
	private Service createTool;
	private Service runTask1;
	private Service runTask2;
	private Service runPreviousTask;
	private Service open;
	
	private Service clearCPC;
	
	


	public EntityImpl() throws Exception
	{
		popupMenuBuilder = Outside.service(this,"gus06.swing.popupmenu.builder1");
		buildMenuItem = Outside.service(this,"gus06.swing.menuitem.builder1");
		
		goToRight =		Outside.service(this,"gus06.swing.tree.cust.file.action.alt_right.gotoright");
		goToLeft =		Outside.service(this,"gus06.swing.tree.cust.file.action.alt_left.gotoleft");
		addNewTab =		Outside.service(this,"gus06.swing.tree.cust.file.action.shift_space.addnewtab");
		copyContent =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_alt_c.copycontent");
		pasteContent =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_alt_v.pastecontent");
		cutContent =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_alt_x.cutcontent");
		copy =			Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_c.copy");
		search =			Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_f.search");
		createDir =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_f1.createdir");
		renameTStamp =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_f2.rename.timestamped");
		duplicateTStamp =	Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_f3.duplicate.timestamped");
		displayInfos2 =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_f4.display.infos2");
		copyPath =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_shift_c.copypath");
		copyName =		Outside.service(this,"gus06.swing.tree.cust.file.action.shift_alt_c.copyname");
		showInFrame =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_shift_space.showinframe");
		pastePath =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_shift_v.pastepath");
		cutPath =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_shift_x.cutpath");
		changeRoot =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_space.changeroot");
		paste =			Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_v.paste");
		cut =			Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_x.cut");
		linkGus =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_k.link_gus");
		remove =			Outside.service(this,"gus06.swing.tree.cust.file.action.delete.remove");
		createFile =		Outside.service(this,"gus06.swing.tree.cust.file.action.f1.createfile");
		renameAsk =		Outside.service(this,"gus06.swing.tree.cust.file.action.f2.rename.ask");
		duplicateAsk =		Outside.service(this,"gus06.swing.tree.cust.file.action.f3.duplicate.ask");
		displayInfos1 =		Outside.service(this,"gus06.swing.tree.cust.file.action.f4.display.infos1");
		refresh =		Outside.service(this,"gus06.swing.tree.cust.file.action.f5.refresh");
		createTool =		Outside.service(this,"gus06.swing.tree.cust.file.action.f6.createtool");
		runTask1 =		Outside.service(this,"gus06.swing.tree.cust.file.action.f7.runtask1");
		runTask2 =		Outside.service(this,"gus06.swing.tree.cust.file.action.f7.runtask2");
		runPreviousTask =	Outside.service(this,"gus06.swing.tree.cust.file.action.alt_f7.runtask.previous");
		open =			Outside.service(this,"gus06.swing.tree.cust.file.action.space.open");
		
		clearCPC = Outside.service(this,"gus06.swing.comp.action.clearcopypastecut");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		clearCPC.p(tree);
		
		JPopupMenu popupMenu = (JPopupMenu) popupMenuBuilder.t(tree);
		
		goToLeft.p(tree);
		goToRight.p(tree);
		
		
		JMenu menuCreate = new JMenu("Create");
		JMenu menuRename = new JMenu("Rename");
		JMenu menuDuplicate = new JMenu("Duplicate");
		JMenu menuInfos = new JMenu("Infos");
		JMenu menuCopy = new JMenu("Copy");
		JMenu menuPaste = new JMenu("Paste");
		JMenu menuCut = new JMenu("Cut");
		JMenu menuShow = new JMenu("Show/Open");
		
		addMenu(tree, menuCreate, createFile, 		"Create file", "F1");
		addMenu(tree, menuCreate, createDir, 		"Create dir", "ctrl F1");
		
		addMenu(tree, menuRename, renameAsk, 		"Rename", "F2");
		addMenu(tree, menuRename, renameTStamp, 	"Rename tstamp", "ctrl F2");
		
		addMenu(tree, menuDuplicate, duplicateAsk, 	"Duplicate", "F3");
		addMenu(tree, menuDuplicate, duplicateTStamp, 	"Duplicate tstamp", "ctrl F3");
		
		addMenu(tree, menuInfos, displayInfos1, 	"Display info 1", "F4");
		addMenu(tree, menuInfos, displayInfos2, 	"Display info 2", "ctrl F4");
		
		addMenu(tree, menuCopy, copy, 			"Copy", "ctrl c");
		addMenu(tree, menuCopy, copyPath, 		"Copy path", "ctrl shift c");
		addMenu(tree, menuCopy, copyContent, 		"Copy content", "ctrl alt c");
		addMenu(tree, menuCopy, copyName, 		"Copy name", "shift alt c");
		
		addMenu(tree, menuPaste, paste, 		"Paste", "ctrl v");
		addMenu(tree, menuPaste, pastePath, 		"Paste path", "ctrl shift v");
		addMenu(tree, menuPaste, pasteContent, 		"Paste content", "ctrl alt v");
		
		addMenu(tree, menuCut, cut, 			"Cut", "ctrl x");
		addMenu(tree, menuCut, cutPath, 		"Cut path", "ctrl shift x");
		addMenu(tree, menuCut, cutContent, 		"Cut content", "ctrl alt x");
		addMenu(tree, menuCut, linkGus, 		"Link", "ctrl k");
		
		addMenu(tree, menuShow, open, 			"Open", "space");
		addMenu(tree, menuShow, changeRoot, 		"Change root", "ctrl space");
		addMenu(tree, menuShow, addNewTab, 		"Add new tab", "shift space");
		addMenu(tree, menuShow, showInFrame, 		"Show in frame", "ctrl shift space");
		
		popupMenu.add(menuCreate);
		popupMenu.add(menuRename);
		popupMenu.add(menuDuplicate);
		popupMenu.add(menuInfos);
		popupMenu.add(menuShow);
		popupMenu.addSeparator();
		popupMenu.add(menuCopy);
		popupMenu.add(menuPaste);
		popupMenu.add(menuCut);
		popupMenu.addSeparator();
		
		addMenu(tree, popupMenu, search, 		"Search", 	"ctrl F");
		addMenu(tree, popupMenu, refresh, 		"Refresh", 	"F5");
		addMenu(tree, popupMenu, remove, 		"Remove",	"DEL");
		addMenu(tree, popupMenu, createTool, 		"Create tool", "F6");
		addMenu(tree, popupMenu, runTask1, 		"Run task", 	"F7");
		addMenu(tree, popupMenu, runTask2, 		"Run custom task", "shift F7");
		addMenu(tree, popupMenu, runPreviousTask, 	"Run previous task", "alt F7");
	}
	
	
	
	
	private void addMenu(JTree tree, JMenu menu, T builder, String title, String key) throws Exception
	{
		menu.add(buildItem(tree, builder, title, key));
	}
	
	private void addMenu(JTree tree, JPopupMenu menu, T builder, String title, String key) throws Exception
	{
		menu.add(buildItem(tree, builder, title, key));
	}
	
	private JMenuItem buildItem(JTree tree, T builder, String title, String key) throws Exception
	{
		E exe = (E) builder.t(tree);
		String display = title+" ("+key+")";
		return (JMenuItem) buildMenuItem.t(new Object[]{exe, display});
	}
}