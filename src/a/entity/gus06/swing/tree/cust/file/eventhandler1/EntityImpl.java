package a.entity.gus06.swing.tree.cust.file.eventhandler1;

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

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140917";}


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
	private Service link;
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
		goToRight =		Outside.service(this,"gus06.swing.tree.cust.file.action.alt_right.gotoright");
		goToLeft =		Outside.service(this,"gus06.swing.tree.cust.file.action.alt_left.gotoleft");
		addNewTab =		Outside.service(this,"gus06.swing.tree.cust.file.action.shift_space.addnewtab");
		copyContent =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_alt_c.copycontent");
		pasteContent =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_alt_v.pastecontent");
		cutContent =		Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_alt_x.cutcontent");
		copy =			Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_c.copy");
		search =Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_f.search");
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
		link =			Outside.service(this,"gus06.swing.tree.cust.file.action.ctrl_k.link_gus");
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
		clearCPC.p(obj);
		
		goToLeft.p(obj);
		goToRight.p(obj);
		addNewTab.p(obj);
		copyContent.p(obj);
		pasteContent.p(obj);
		cutContent.p(obj);
		copy.p(obj);
		search.p(obj);
		createDir.p(obj);
		renameTStamp.p(obj);
		duplicateTStamp.p(obj);
		displayInfos2.p(obj);
		copyPath.p(obj);
		copyName.p(obj);
		showInFrame.p(obj);
		pastePath.p(obj);
		cutPath.p(obj);
		changeRoot.p(obj);
		paste.p(obj);
		cut.p(obj);
		link.p(obj);
		remove.p(obj);
		createFile.p(obj);
		renameAsk.p(obj);
		duplicateAsk.p(obj);
		displayInfos1.p(obj);
		refresh.p(obj);
		createTool.p(obj);
		runTask1.p(obj);
		runTask2.p(obj);
		runPreviousTask.p(obj);
		open.p(obj);
	}
}