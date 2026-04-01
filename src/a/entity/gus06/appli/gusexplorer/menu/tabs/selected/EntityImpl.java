package a.entity.gus06.appli.gusexplorer.menu.tabs.selected;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20151006";}

	private Service reload;
	private Service moveUp;
	private Service runTask;
	private Service runPreviousTask;
	private Service custLabel;
	private Service refactorPath;
	private Service addToConfigs;
	private Service toClipboard;
	private Service toClipboardName;
	private Service toClipboardPath;
	private Service browseParent;
	private Service showInFrame;
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		reload = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.reload");
		moveUp = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.moveup");
		runTask = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.runtask");
		runPreviousTask = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.runtask.previous");
		custLabel = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.custlabel");
		refactorPath = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.refactorpath");
		addToConfigs = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.addtoconfigs");
		toClipboard = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.toclipboard");
		toClipboardName = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.toclipboard.name");
		toClipboardPath = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.toclipboard.path");
		browseParent = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.browseparent");
		showInFrame = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.selected.showinframe");
	
		menu = new JMenu("Selected tab");
		
		add(reload);
		add(moveUp);
		add(showInFrame);
		add(browseParent);
		menu.addSeparator();
		add(custLabel);
		add(refactorPath);
		add(addToConfigs);
		menu.addSeparator();
		add(runTask);
		add(runPreviousTask);
		menu.addSeparator();
		add(toClipboard);
		add(toClipboardName);
		add(toClipboardPath);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}